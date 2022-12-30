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
import com.example.alfaomega.BLUETOOTH_STATE
import com.example.alfaomega.MainActivity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import java.io.IOException
import java.util.*

class BluetoothViewModel: ViewModel() {

    val LF: Byte = 0x0A

    val RESET_PRINTER = byteArrayOf(0x1B, 0x40)

    val TEXT_ALIGN_LEFT = byteArrayOf(0x1B, 0x61, 0x00)
    val TEXT_ALIGN_CENTER = byteArrayOf(0x1B, 0x61, 0x01)
    val TEXT_ALIGN_RIGHT = byteArrayOf(0x1B, 0x61, 0x02)

    val TEXT_WEIGHT_NORMAL = byteArrayOf(0x1B, 0x45, 0x00)
    val TEXT_WEIGHT_BOLD = byteArrayOf(0x1B, 0x45, 0x01)

    val LINE_SPACING_24 = byteArrayOf(0x1b, 0x33, 0x18)
    val LINE_SPACING_30 = byteArrayOf(0x1b, 0x33, 0x1e)

    val TEXT_FONT_A = byteArrayOf(0x1B, 0x4D, 0x00)
    val TEXT_FONT_B = byteArrayOf(0x1B, 0x4D, 0x01)
    val TEXT_FONT_C = byteArrayOf(0x1B, 0x4D, 0x02)
    val TEXT_FONT_D = byteArrayOf(0x1B, 0x4D, 0x03)
    val TEXT_FONT_E = byteArrayOf(0x1B, 0x4D, 0x04)

    val TEXT_SIZE_NORMAL = byteArrayOf(0x1D, 0x21, 0x00)
    val TEXT_SIZE_DOUBLE_HEIGHT = byteArrayOf(0x1D, 0x21, 0x01)
    val TEXT_SIZE_DOUBLE_WIDTH = byteArrayOf(0x1D, 0x21, 0x10)
    val TEXT_SIZE_BIG = byteArrayOf(0x1D, 0x21, 0x11)
    val TEXT_SIZE_BIG_2 = byteArrayOf(0x1D, 0x21, 0x22)
    val TEXT_SIZE_BIG_3 = byteArrayOf(0x1D, 0x21, 0x33)
    val TEXT_SIZE_BIG_4 = byteArrayOf(0x1D, 0x21, 0x44)
    val TEXT_SIZE_BIG_5 = byteArrayOf(0x1D, 0x21, 0x55)
    val TEXT_SIZE_BIG_6 = byteArrayOf(0x1D, 0x21, 0x66)

    val TEXT_UNDERLINE_OFF = byteArrayOf(0x1B, 0x2D, 0x00)
    val TEXT_UNDERLINE_ON = byteArrayOf(0x1B, 0x2D, 0x01)
    val TEXT_UNDERLINE_LARGE = byteArrayOf(0x1B, 0x2D, 0x02)

    val TEXT_DOUBLE_STRIKE_OFF = byteArrayOf(0x1B, 0x47, 0x00)
    val TEXT_DOUBLE_STRIKE_ON = byteArrayOf(0x1B, 0x47, 0x01)

    val TEXT_COLOR_BLACK = byteArrayOf(0x1B, 0x72, 0x00)
    val TEXT_COLOR_RED = byteArrayOf(0x1B, 0x72, 0x01)

    val TEXT_COLOR_REVERSE_OFF = byteArrayOf(0x1D, 0x42, 0x00)
    val TEXT_COLOR_REVERSE_ON = byteArrayOf(0x1D, 0x42, 0x01)


    val BARCODE_TYPE_UPCA = 65
    val BARCODE_TYPE_UPCE = 66
    val BARCODE_TYPE_EAN13 = 67
    val BARCODE_TYPE_EAN8 = 68
    val BARCODE_TYPE_ITF = 70
    val BARCODE_TYPE_128 = 73

    val BARCODE_TEXT_POSITION_NONE = 0
    val BARCODE_TEXT_POSITION_ABOVE = 1
    val BARCODE_TEXT_POSITION_BELOW = 2

    val QRCODE_1 = 49
    val QRCODE_2 = 50

    lateinit var bluetoothManager: BluetoothManager
    lateinit var bluetoothAdapter: BluetoothAdapter
    var bluetoothSocket: BluetoothSocket? = null

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    var ActivityApp : MainActivity? = null

    var context : Context? = null

    lateinit var devices: Set<BluetoothDevice>

    fun createInstance(appCompatActivity: MainActivity){
        bluetoothManager = appCompatActivity.applicationContext.getSystemService(AppCompatActivity.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter

        Log.i("CheckBluetooth", ":request permission.....")

        activityResultLauncher = appCompatActivity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                Log.i("CheckBluetooth", ":request permission result ok")
            } else {
                Log.i("CheckBluetooth", ":request permission result canceled / denied")
            }
        }

        ActivityApp = appCompatActivity
        context = appCompatActivity
    }

    fun requestBluetoothPermission() : Boolean {

        var statPermission : Boolean = false

        if (bluetoothAdapter?.isEnabled == false) {
            Log.i("CheckBluetooth", ":Bluetooth Off Condition Wanna Turn On?")
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            activityResultLauncher.launch(enableBluetoothIntent)

            statPermission = false
        }
        else{
            Log.i("CheckBluetooth", ":Bluetooth On Condition")
            statPermission = true
//            STAT_BLUETOOTH = true
        }
        return statPermission
    }

    fun checkBluetoothCompatible(){
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Log.i("CheckBluetooth", ":Device not Supported BLuetooth")
        }
        else{
            Log.i("CheckBluetooth", ":Device Supported BLuetooth")
        }
    }

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

    fun sendCommandAlign(){
        if(bluetoothSocket != null){
            try{
                bluetoothSocket!!.outputStream.write(RESET_PRINTER)
//                bluetoothSocket!!.outputStream.write(TEXT_ALIGN_RIGHT)
//                bluetoothSocket!!.outputStream.write(TEXT_SIZE_NORMAL)
//                bluetoothSocket!!.outputStream.write(TEXT_FONT_D)
//                bluetoothSocket!!.outputStream.write(TEXT_UNDERLINE_ON)
//                bluetoothSocket!!.outputStream.write(10)
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun connectBluetooth(
        device: BluetoothDevice,
        address: String,
        uuidDevice: String,
        context: Context,
        multiplePermissionState: MultiplePermissionsState
    ){
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            multiplePermissionState.launchMultiplePermissionRequest()
            return
        }
        bluetoothSocket = bluetoothAdapter!!.getRemoteDevice(address).createRfcommSocketToServiceRecord(
            UUID.fromString(uuidDevice))
//        Log.i("Bluetooth_device", "${device.uuids[1].toString()}")
        try {

            bluetoothAdapter.cancelDiscovery()
            bluetoothSocket!!.connect()

            if (bluetoothSocket!!.isConnected){
                Log.i("Bluetooth", "Connected")
            }
            else{
                Log.i("Bluetooth", "Disconnect")
            }
        }
        catch (e: Exception){
            Log.i("Bluetooth", "Error")
        }
    }

    fun disconnectBluetooth(){
        if(bluetoothSocket != null){
            try {
                bluetoothSocket!!.close()
                bluetoothSocket = null
                if (bluetoothSocket == null){
                    Log.i("Bluetooth", "Disconnect")
                }
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @OptIn(ExperimentalPermissionsApi::class)
    fun showPairedDevice(context: Context, multiplePermissionState: MultiplePermissionsState){

        BLUETOOTH_STATE = 0

        val pairedDevices = if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            multiplePermissionState.launchMultiplePermissionRequest()
            return
        }
        else
            bluetoothAdapter.bondedDevices

        devices = pairedDevices

        if (devices.isNotEmpty()) {
            // Show a list of paired devices here
            for (device in devices) {
                Log.i("Bluetooth_device", "${device.name} -- ${device.type} -- ${device.address} -- ${device.uuids[0]}")
            }
            BLUETOOTH_STATE = 1
        }
    }
}