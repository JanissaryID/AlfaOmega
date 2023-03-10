package com.example.alfaomega.bluetoothprinter

object CommandPrinter {
    val LF: Byte = 0x0A

    val RESET_PRINTER = byteArrayOf(0x1B, 0x40)

    val TEXT_ALIGN_LEFT = byteArrayOf(0x1B, 0x61, 0x00)
    val TEXT_ALIGN_CENTER = byteArrayOf(0x1B, 0x61, 0x01)
    val TEXT_ALIGN_RIGHT = byteArrayOf(0x1B, 0x61, 0x02)

    val TEXT_WEIGHT_NORMAL = byteArrayOf(0x1B, 0x45, 0x00)
    val TEXT_WEIGHT_BOLD = byteArrayOf(0x1B, 0x45, 0x01)

    val LINE_SPACING_24 = byteArrayOf(0x1B, 0x33, 0x18)
    val LINE_SPACING_30 = byteArrayOf(0x1B, 0x33, 0x1e)

    val TEXT_CHAR_A = byteArrayOf(0x1B, 0x4D, 0)
    val TEXT_CHAR_B = byteArrayOf(0x1B, 0x4D, 1)

    val TEXT_CPI_A = byteArrayOf(0x1B, 0xC1.toByte(), 0)
    val TEXT_CPI_B = byteArrayOf(0x1B, 0xC1.toByte(), 1)
    val TEXT_CPI_C = byteArrayOf(0x1B, 0xC1.toByte(), 2)

    val TEXT_FONT_A = byteArrayOf(0x1B, 0x50)
    val TEXT_FONT_C = byteArrayOf(0x1B, 0x54)
    val TEXT_FONT_D = byteArrayOf(0x1B, 0x55)
//    val TEXT_FONT_D = byteArrayOf(0x1B, 0x56)
//    val TEXT_FONT_E = byteArrayOf(0x1B, 0x4D)

    val TEXT_SIZE_NORMAL = byteArrayOf(0x1D, 0x21, 0x00)
    val TEXT_SIZE_DOUBLE_HEIGHT = byteArrayOf(0x1D, 0x21, 0x01)
    val TEXT_SIZE_DOUBLE_WIDTH = byteArrayOf(0x1D, 0x21, 0x20)
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
}