package com.example.alfaomega.api.transaction

import com.google.gson.annotations.SerializedName

data class TransactionModel(

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("transaction_class")
	val transactionClass: Boolean? = null,

	@field:SerializedName("transaction_menu")
	val transactionMenu: String? = null,

	@field:SerializedName("transaction_price")
	val transactionPrice: String? = null,

	@field:SerializedName("is_washer")
	val isWasher: Boolean? = null,

	@field:SerializedName("transaction_payment")
	val transactionPayment: Boolean? = null,

	@field:SerializedName("is_dryer")
	val isDryer: Boolean? = null,

	@field:SerializedName("transaction_store")
	val transactionStore: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("transaction_admin")
	val transactionAdmin: String? = null,

	@field:SerializedName("transaction_customer")
	val transactionCustomer: String? = null,

	@field:SerializedName("transaction_state_machine")
	val transactionStateMachine: Int? = null
)
