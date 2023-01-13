package com.example.alfaomega.websket

import com.google.protobuf.ByteString

data class SocketUpdate(
    val text: String? = null,
    val byteString: ByteString? = null,
    val exception: Throwable? = null
)