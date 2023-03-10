package com.example.alfaomega.api.qr

import com.google.gson.annotations.SerializedName

data class QrModel(

	@field:SerializedName("key_id")
	val keyId: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("merchant_id")
	val merchantId: String? = null,

	@field:SerializedName("ownerid")
	val ownerid: String? = null,

	@field:SerializedName("client_id")
	val clientId: String? = null
)
