package com.example.alfaomega.whatsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.alfaomega.*

class WhatsappViewModel: ViewModel() {

    val Finish_Message: String =
            "Laundry atas nama : *${TRANSACATION_CUSTOMER}* %0a" +
            "%0a" +
            "Dengan layanan : *${TRANSACATION_MENU}*. %0a" +
            "%0a" +
            "Pada tanggal : $TRANSACATION_DATE %0a" +
            "%0a" +
            "Sudah selesai, silahkan di ambil di $STORE_NAME, jalan " +
            "$STORE_ADDRESS ${STORE_CITY}." +
            "%0a" +
            "%0a" +
            "Terimakasih \uD83D\uDE42\n"

    val Send_Message: String =
            "Laundry atas nama : *${TRANSACATION_CUSTOMER}* %0a" +
            "%0a" +
            "Dengan layanan : *${TRANSACATION_MENU}*. %0a" +
            "%0a" +
            "Pada tanggal : $TRANSACATION_DATE %0a" +
            "%0a" +
            "Dalam proses pengerjaan" +
            "%0a" +
            "%0a" +
            "Terimakasih \uD83D\uDE42\n"

    fun SendMessage(context: Context, message: String, phone: String){
        context.startActivity(
            // on below line we are opening the intent.
            Intent(
                // on below line we are calling
                // uri to parse the data
                Intent.ACTION_VIEW,
                Uri.parse(
                    // on below line we are passing uri,
                    // message and whats app phone number.
                    String.format(
                        "https://api.whatsapp.com/send?phone=%s&text=%s",
                        phone,
                        message
                    )
                )
            )
        )
    }
}