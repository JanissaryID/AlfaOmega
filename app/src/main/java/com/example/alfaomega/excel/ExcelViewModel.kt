package com.example.alfaomega.excel

import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.alfaomega.*
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.WritableCellFormat
import jxl.write.WritableFont
import jxl.write.WritableWorkbook
import java.io.File
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ExcelViewModel: ViewModel() {

    var stateExcel: Int by mutableStateOf(0)

    var workbook: WritableWorkbook? = null

    lateinit var formatFont: WritableCellFormat

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun createExcelSheetTransaction(date: String) {
        var dateTitle = date
        var csvFile = ""


        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)

        if(dateTitle == ""){
            csvFile = "Laporan Transaksi ${formatted}.xls"
        }
        else{
            csvFile = "Laporan Transaksi ${date}.xls"
        }

        val futureStudioIconFile = File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + "/" + csvFile
        )
        val wbSettings = WorkbookSettings()
        wbSettings.locale = Locale("en", "EN")
        try {
            workbook = Workbook.createWorkbook(futureStudioIconFile, wbSettings)
//            createFirstSheet()
            workbook?.write()
            workbook?.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun createExcelSheet(date: String) {
        var dateTitle = date
        var csvFile = ""

        formatFont = WritableCellFormat()

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)

        if(dateTitle == ""){
            csvFile = "Laporan ${formatted}.xls"
        }
        else{
            csvFile = "Laporan ${date}.xls"
        }

        val futureStudioIconFile = File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString() + "/" + csvFile
        )
        val wbSettings = WorkbookSettings()
        wbSettings.locale = Locale("en", "EN")
        try {
            workbook = Workbook.createWorkbook(futureStudioIconFile, wbSettings)
            createReportMachine(if(dateTitle.isNullOrEmpty()) formatted else date)
            workbook?.write()
            workbook?.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.i("my_excel", "Exception 1 : ${e.printStackTrace()}")
        }
    }

    fun createReportMachine(date: String) {

        stateExcel = 0

        try {
            TransactionReport(date = date, SheetTitle = "Transaksi")
            MachineReport(date = date, SheetTitle = "Mesin")
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.i("my_excel", "Exception 2 : ${e.printStackTrace()}")
        }
    }

    fun TransactionReport(date: String, SheetTitle: String){
        val sheet = workbook!!.createSheet(SheetTitle, 0)
        var rowExcel = 0
        var totalPriceQris = 0
        var totalPriceCash = 0

        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.setMaximumFractionDigits(0)

        sheet.setColumnView(0,4)
        sheet.setColumnView(1,35)
        sheet.setColumnView(2,20)
        sheet.setColumnView(3,20)
        sheet.setColumnView(4,20)

        sheet.mergeCells(0,0,1,0)
        sheet.mergeCells(2,0,3,0)
        sheet.mergeCells(0,1,1,1)
        sheet.mergeCells(2,1,3,1)

        sheet.mergeCells(0,3,1,3)
        sheet.mergeCells(2,3,3,3)

        sheet.addCell(Label(0, 0, "Toko", FormatExcelColumn(format = 5)))
        sheet.addCell(Label(2, 0, ": ${STORE_NAME}", FormatExcelColumn(format = 5)))
        sheet.addCell(Label(0, 1, "Alamat", FormatExcelColumn(format = 5)))
        sheet.addCell(Label(2, 1, ": ${STORE_ADDRESS}", FormatExcelColumn(format = 5)))

        sheet.addCell(Label(0, 3, "Tanggal", FormatExcelColumn(format = 5)))
        sheet.addCell(Label(2, 3, ": ${date}", FormatExcelColumn(format = 5)))

//            // column and row title
        sheet.addCell(Label(0, 5, "NO", FormatExcelColumn(format = 1)))
        sheet.addCell(Label(1, 5, "NAMA MENU", FormatExcelColumn(format = 1)))
        sheet.addCell(Label(2, 5, "TYPE MENU", FormatExcelColumn(format = 1)))
        sheet.addCell(Label(3, 5, "PEMBAYARAN", FormatExcelColumn(format = 1)))
        sheet.addCell(Label(4, 5, "HARGA", FormatExcelColumn(format = 1)))

        for ((index, value) in TRANSACTION_RESPONSE.withIndex()) {
//                rowExcel
            sheet.addCell(Label(0, index + 6, (index + 1).toString(), FormatExcelColumn(format = 2)))
            sheet.addCell(Label(1, index + 6, TRANSACTION_RESPONSE[index].transactionMenu, FormatExcelColumn(format = 6)))
            sheet.addCell(Label(2, index + 6, if(TRANSACTION_RESPONSE[index].transactionClass!!) "Titan" else "Giant", FormatExcelColumn(format = 2)))
            sheet.addCell(Label(3, index + 6, if(TRANSACTION_RESPONSE[index].transactionPayment!!) "Qris" else "Cash", FormatExcelColumn(format = 2)))
            sheet.addCell(Label(4, index + 6, numberFormat.format(TRANSACTION_RESPONSE[index].transactionPrice!!.toInt()), FormatExcelColumn(format = 8)))

            rowExcel = index
            if(TRANSACTION_RESPONSE[index].transactionPayment!!){
                totalPriceQris += TRANSACTION_RESPONSE[index].transactionPrice!!.toInt()
            }
            else{
                totalPriceCash += TRANSACTION_RESPONSE[index].transactionPrice!!.toInt()
            }
        }

        sheet.mergeCells(0, rowExcel + 7, 3, rowExcel + 7)
        sheet.addCell(Label(0, rowExcel + 7, "Jumlah", FormatExcelColumn(format = 3)))

        sheet.addCell(Label(4, rowExcel + 7, numberFormat.format((totalPriceQris + totalPriceCash)), FormatExcelColumn(format = 9)))

        sheet.addCell(Label(1, rowExcel + 9, "Jumlah Pembayaran Tunai", FormatExcelColumn(format = 7)))
        sheet.addCell(Label(1, rowExcel + 10, "Jumlah Pembayaran Qris", FormatExcelColumn(format = 7)))

        sheet.addCell(Label(2, rowExcel + 9, ": ${numberFormat.format(totalPriceCash)}", FormatExcelColumn(format = 10)))
        sheet.addCell(Label(2, rowExcel + 10, ": ${numberFormat.format(totalPriceQris)}", FormatExcelColumn(format = 10)))


    }

    fun MachineReport(date: String, SheetTitle: String) {
        val sheet = workbook!!.createSheet(SheetTitle, 1)
        var rowExcel = 0
        var totalPrice = 0

        var totalWahser = 0
        var totalDryer = 0

        var totalWahserTitan = 0
        var totalDryerTitan = 0

        sheet.setColumnView(0,4)
        sheet.setColumnView(1,15)
        sheet.setColumnView(2,15)
        sheet.setColumnView(3,15)
        sheet.setColumnView(4,15)
        sheet.setColumnView(5,15)
        sheet.setColumnView(6,15)
        sheet.setColumnView(7,15)

        sheet.mergeCells(0,0,1,0)
        sheet.mergeCells(2,0,3,0)
        sheet.mergeCells(0,1,1,1)
        sheet.mergeCells(2,1,3,1)

        sheet.mergeCells(0,3,1,3)
        sheet.mergeCells(2,3,3,3)

        sheet.addCell(Label(0, 0, "Toko", FormatExcelColumn(format = 5)))
        sheet.addCell(Label(2, 0, ": ${STORE_NAME}", FormatExcelColumn(format = 5)))
        sheet.addCell(Label(0, 1, "Alamat", FormatExcelColumn(format = 5)))
        sheet.addCell(Label(2, 1, ": ${STORE_ADDRESS}", FormatExcelColumn(format = 5)))

        sheet.addCell(Label(0, 3, "Tanggal", FormatExcelColumn(format = 5)))
        sheet.addCell(Label(2, 3, ": ${date}", FormatExcelColumn(format = 5)))

        sheet.mergeCells(0,5,0,6)
        sheet.mergeCells(1,5,1,6)
        sheet.mergeCells(2,5,2,6)
        sheet.mergeCells(3,5,3,6)
        sheet.mergeCells(4,5,4,6)

        // column and row title
        sheet.addCell(Label(0, 5, "NO", FormatExcelColumn(format = 1)))
        sheet.addCell(Label(1, 5, "JAM",FormatExcelColumn(format = 1)))
        sheet.addCell(Label(2, 5, "NO MESIN",FormatExcelColumn(format = 1)))
        sheet.addCell(Label(3, 5, "WASHER",FormatExcelColumn(format = 1)))
        sheet.addCell(Label(4, 5, "DRYER",FormatExcelColumn(format = 1)))

        for ((index, value) in LIST_LOG.withIndex()) {
            sheet.addCell(Label(0, index + 7, (index + 1).toString(), FormatExcelColumn(format = 2)))
            sheet.addCell(Label(1, index + 7, LIST_LOG[index].time.toString(), FormatExcelColumn(format = 2)))
            sheet.addCell(Label(2, index + 7, LIST_LOG[index].machineNumber.toString(), FormatExcelColumn(format = 2)))

            if(!LIST_LOG[index].machineType!!){
                sheet.addCell(Label(3, index + 7, "✔", FormatExcelColumn(format = 2)))
                sheet.addCell(Label(4, index + 7, " ", FormatExcelColumn(format = 2)))
                if(!LIST_LOG[index].machineClass!!){
                    totalWahser++
                }
                else{
                    totalWahserTitan++
                }

            }
            else if(LIST_LOG[index].machineType!!){
                sheet.addCell(Label(3, index + 7, " ", FormatExcelColumn(format = 2)))
                sheet.addCell(Label(4, index + 7, "✔", FormatExcelColumn(format = 2)))
                if(!LIST_LOG[index].machineClass!!){
                    totalDryer++
                }
                else{
                    totalDryerTitan++
                }

            }
            rowExcel = index
        }

        sheet.mergeCells(0, rowExcel + 8, 2, rowExcel + 8)
        sheet.addCell(Label(0, rowExcel + 8, "Jumlah", FormatExcelColumn(format = 3)))

        sheet.addCell(Label(3, rowExcel + 8, (totalWahser+totalWahserTitan).toString(), FormatExcelColumn(format = 3)))
        sheet.addCell(Label(4, rowExcel + 8, (totalDryer+totalDryerTitan).toString(), FormatExcelColumn(format = 3)))

        sheet.mergeCells(1, rowExcel + 10,2,rowExcel + 10)
        sheet.mergeCells(1, rowExcel + 11,2,rowExcel + 11)
        sheet.mergeCells(1, rowExcel + 12,2,rowExcel + 12)
        sheet.mergeCells(1, rowExcel + 13,2,rowExcel + 13)

        sheet.addCell(Label(1, rowExcel + 10, "Mesin Cuci Kecil", FormatExcelColumn(format = 7)))
        sheet.addCell(Label(1, rowExcel + 11, "Mesin Kering Kecil", FormatExcelColumn(format = 7)))
        sheet.addCell(Label(1, rowExcel + 12, "Mesin Cuci Besar", FormatExcelColumn(format = 7)))
        sheet.addCell(Label(1, rowExcel + 13, "Mesin Kering Besar", FormatExcelColumn(format = 7)))

        sheet.addCell(Label(3, rowExcel + 10, ": $totalDryer", FormatExcelColumn(format = 7)))
        sheet.addCell(Label(3, rowExcel + 11, ": $totalDryer", FormatExcelColumn(format = 7)))
        sheet.addCell(Label(3, rowExcel + 12, ": $totalWahserTitan", FormatExcelColumn(format = 7)))
        sheet.addCell(Label(3, rowExcel + 13, ": $totalDryerTitan", FormatExcelColumn(format = 7)))

        stateExcel = 1
    }

    fun FormatExcelColumn(format: Int): WritableCellFormat{

        when(format){
            1 -> {
                val font1 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
                val format1 = WritableCellFormat(font1)
                format1.alignment = Alignment.CENTRE
                format1.verticalAlignment = VerticalAlignment.CENTRE
                format1.setBorder(Border.ALL, BorderLineStyle.THIN)
                formatFont = format1
            }
            2 -> {
                val font2 = WritableFont(WritableFont.ARIAL, 11)
                val format2 = WritableCellFormat(font2)
                format2.alignment = Alignment.CENTRE
                format2.setBorder(Border.ALL, BorderLineStyle.THIN)
                formatFont = format2
            }
            3 -> {
                val font3 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
                val format3 = WritableCellFormat(font3)
                format3.setBorder(Border.ALL, BorderLineStyle.THIN)
                format3.alignment = Alignment.CENTRE
                formatFont = format3
            }
            4 -> {
                val format4 = WritableCellFormat()
                format4.setBorder(Border.ALL, BorderLineStyle.THIN)
                formatFont = format4
            }
            5 -> {
                val font5 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
                val format5 = WritableCellFormat(font5)
                format5.alignment = Alignment.LEFT
                formatFont = format5
            }
            6 -> {
                val font6 = WritableFont(WritableFont.ARIAL, 11)
                val format6 = WritableCellFormat(font6)
                format6.alignment = Alignment.LEFT
                format6.setBorder(Border.ALL, BorderLineStyle.THIN)
                formatFont = format6
            }
            7 -> {
                val font7 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
                val format7 = WritableCellFormat(font7)
                format7.alignment = Alignment.LEFT
//                format6.setBorder(Border.ALL, BorderLineStyle.THIN)
                formatFont = format7
            }
            8 -> {
                val font8 = WritableFont(WritableFont.ARIAL, 11)
                val format8 = WritableCellFormat(font8)
                format8.alignment = Alignment.RIGHT
                format8.setBorder(Border.ALL, BorderLineStyle.THIN)
                formatFont = format8
            }
            9 -> {
                val font9 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
                val format9 = WritableCellFormat(font9)
                format9.alignment = Alignment.RIGHT
                format9.setBorder(Border.ALL, BorderLineStyle.THIN)
                formatFont = format9
            }
            10 -> {
                val font10 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
                val format10 = WritableCellFormat(font10)
                format10.alignment = Alignment.LEFT
//                format10.setBorder(Border.ALL, BorderLineStyle.THIN)
                formatFont = format10
            }
        }
        return formatFont
    }
}