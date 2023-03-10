package com.example.alfaomega.bluetoothprinter

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alfaomega.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BluetoothViewModel: ViewModel() {

    lateinit var bluetoothManager: BluetoothManager
    lateinit var bluetoothAdapter: BluetoothAdapter
    var bluetoothSocket: BluetoothSocket? = null

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    var ActivityApp : MainActivity? = null

    var context : Context? = null

    var devices: Set<BluetoothDevice>? = null
    @ExperimentalCoroutinesApi
    fun createInstance(appCompatActivity: MainActivity){
        bluetoothManager = appCompatActivity.applicationContext.getSystemService(AppCompatActivity.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter

        Log.i("Bluetooth_debug", ":request permission.....")

        activityResultLauncher = appCompatActivity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                Log.i("Bluetooth_debug", ":request permission result ok")
            } else {
                Log.i("Bluetooth_debug", ":request permission result canceled / denied")
            }
        }

        ActivityApp = appCompatActivity
        context = appCompatActivity


    }
    @ExperimentalCoroutinesApi
    fun requestBluetoothPermission(){
        try {
            if (bluetoothAdapter?.isEnabled == false) {
                Log.i("Bluetooth_debug", ":Bluetooth Off Condition Wanna Turn On?")
                val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                activityResultLauncher.launch(enableBluetoothIntent)
                BLUETOOTH_STATE_ON = false
            }
            else{
                Log.i("Bluetooth_debug", ":Bluetooth On Condition")
                BLUETOOTH_STATE_ON = true
//            STAT_BLUETOOTH = true
            }
        }
        catch (e: Exception){
            Log.i("Bluetooth_debug", "Error Bluetooth : $e")
            BLUETOOTH_STATE_ON = false
        }
    }
    @ExperimentalCoroutinesApi
    fun checkBluetoothCompatible(){
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Log.i("Bluetooth_debug", ":Device not Supported BLuetooth")
        }
        else{
            Log.i("Bluetooth_debug", ":Device Supported BLuetooth")
        }
    }
    @ExperimentalCoroutinesApi
    fun sendCommand(input: String){
        if(bluetoothSocket != null){
            try{
                bluetoothSocket!!.outputStream.write(input.toByteArray())
                bluetoothSocket!!.outputStream.write(10)
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }
    @ExperimentalCoroutinesApi
    fun sendCommandDivider(input: String){
        if(bluetoothSocket != null){
            try{
                for(i in 0..31){
                    bluetoothSocket!!.outputStream.write(input.toByteArray())
                }
                bluetoothSocket!!.outputStream.write(10)
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun sendCommandAbsolutePosition(leftString: String, rightString: String){
        //31
        var spacingLength = 31 - (leftString.length + rightString.length)

        var finalString = "${leftString.padEnd(spacingLength + leftString.length + 1, ' ')}${rightString}"
//        Log.d("log_print", "$finalString - ${finalString.length}")
        if(bluetoothSocket != null){
            try{
                bluetoothSocket!!.outputStream.write(finalString.toByteArray())
                bluetoothSocket!!.outputStream.write(10)
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun sendCommandAlign(command: ByteArray){
        if(bluetoothSocket != null){
            try{
                bluetoothSocket!!.outputStream.write(command)
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }
    @ExperimentalCoroutinesApi
    fun sendCommandAlign(command: Byte){
        if(bluetoothSocket != null){
            try{
                bluetoothSocket!!.outputStream.write(command.toInt())
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalPermissionsApi::class)
    @ExperimentalCoroutinesApi
    fun connectBluetooth(
        address: String,
        uuidDevice: String,
        context: Context,
        multiplePermissionState: MultiplePermissionsState
    ){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    multiplePermissionState.launchMultiplePermissionRequest()
                }
                bluetoothSocket = bluetoothAdapter!!.getRemoteDevice(address)
                    .createRfcommSocketToServiceRecord(UUID.fromString(uuidDevice))
                try {
                    bluetoothAdapter.cancelDiscovery()
                    bluetoothSocket!!.connect()
                    Log.i("Bluetooth_debug", "Connecting")
                    if (bluetoothSocket!!.isConnected){
                        STAT_BLUETOOTH_CONNECT = true
                        Log.i("Bluetooth_debug", "Connected")
                        writeNota()
                    }
                    else{
                        STAT_BLUETOOTH_CONNECT = false
                        Log.i("Bluetooth_debug", "Disconnect")
                    }
                }
                catch (e: Exception){
                    STAT_BLUETOOTH_CONNECT = false
                    Log.i("Bluetooth_debug", "Error ${e}")
                }
            }
            catch (e: Exception){
                STAT_BLUETOOTH_CONNECT = false
                Log.i("Bluetooth_debug", "Error ${e}")
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun disconnectBluetooth(){
        if(bluetoothSocket != null){
            try {
                bluetoothSocket!!.close()
                bluetoothSocket = null
                if (bluetoothSocket == null){
                    Log.i("Bluetooth_debug", "Disconnect")
                }
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    @ExperimentalCoroutinesApi
    fun showPairedDevice(context: Context, multiplePermissionState: MultiplePermissionsState){

        BLUETOOTH_STATE = 0

        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.i("Bluetooth_debug", "Try Bluetooth")
                val pairedDevices = if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    multiplePermissionState.launchMultiplePermissionRequest()
                }
                else
                    bluetoothAdapter.bondedDevices

                devices = bluetoothAdapter.bondedDevices

                if (devices!!.isNotEmpty()) {
                    // Show a list of paired devices here
                    for (device in devices!!) {
                        Log.i("Bluetooth_debug", "${device.name} -- ${device.type} -- ${device.address}")
                    }
                    BLUETOOTH_STATE = 1
                }
            }
            catch(e: IOException){
                e.printStackTrace()
                Log.i("Bluetooth_debug", "${e.printStackTrace()}")
            }
        }
    }
//    @ExperimentalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.O)
fun writeNota(){
        var idTransaction = "${TRANSACATION_ID.substring(0,5)}${TRANSACATION_ID.substring(
            TRANSACATION_ID.length-5, TRANSACATION_ID.length)}"

        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val timeNow = LocalDateTime.now().format(formatter)

        try {

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_CHAR_B)
            sendCommandAlign(command = CommandPrinter.TEXT_SIZE_DOUBLE_WIDTH)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_CENTER)
            sendCommand("$STORE_NAME")
            sendCommandAlign(command = CommandPrinter.LF)

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_CENTER)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommand("-- Nota Transakasi --")
            sendCommandAlign(command = CommandPrinter.LF)

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_CENTER)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommand("$STORE_ADDRESS")
            sendCommandAlign(command = CommandPrinter.LF)

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_CENTER)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommand("$STORE_PHONE")
            sendCommandAlign(command = CommandPrinter.LF)

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandAbsolutePosition("Pelanggan", "${TRANSACATION_CUSTOMER}")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandDivider("-")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandAbsolutePosition("Lunas", "No. ${idTransaction}")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandDivider("-")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandAbsolutePosition("${TRANSACATION_DATE} $timeNow", "${TRANSACATION_ADMIN}")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandDivider("-")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_BOLD)
            sendCommand("${if(TRANSACATION_CLASS) "(Mesin Besar) " else "(Mesin Kecil)"} $TRANSACATION_MENU")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandDivider("-")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandAbsolutePosition("PEMBAYARAN", "${TRANSACATION_PAYMENT}")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandAbsolutePosition("TOTAL", "${TRANSACATION_PRICE}")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandAbsolutePosition("BAYAR", "${TRANSACTION_MONEY}")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandAbsolutePosition("KEMBALI", "${TRANSACTION_MONEY.toInt() - TRANSACATION_PRICE.toInt()}")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandDivider("-")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_CENTER)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommand("-- Estimasi Waktu --")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_CENTER)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommand("Estimasi +/- 2 Jam")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommandDivider("-")

            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_CENTER)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_NORMAL)
            sendCommand("-- Syarat Dan Ketentuan --")
            sendCommandAlign(command = CommandPrinter.LF)

            LIST_RULE.forEachIndexed{ index, rule ->
                sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
                sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_LEFT)
                sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_BOLD)
                sendCommand("${index+1}. ${rule.rule}")
            }

            sendCommandAlign(command = CommandPrinter.LF)
            sendCommandAlign(command = CommandPrinter.RESET_PRINTER)
            sendCommandAlign(command = CommandPrinter.TEXT_ALIGN_CENTER)
            sendCommandAlign(command = CommandPrinter.TEXT_WEIGHT_BOLD)
            sendCommandAlign(command = CommandPrinter.LF)
            sendCommand("Terimakasih")

            //End of Transaction
            sendCommandAlign(command = CommandPrinter.LF)
            sendCommandAlign(command = CommandPrinter.LF)
            sendCommandAlign(command = CommandPrinter.LF)
        }
        catch(e: IOException){
            e.printStackTrace()
            Log.i("Bluetooth_debug", "Error ${e}")
        }
    }
}