package com.example.alfaomega.api.user

import com.google.gson.annotations.SerializedName

data class UserModel(

	@field:SerializedName("password_user")
	val passwordUser: String? = null,

	@field:SerializedName("id_owner")
	val idOwner: String? = null,

	@field:SerializedName("type_user")
	val typeUser: Int? = null,

	@field:SerializedName("statususer")
	val statususer: Boolean? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
