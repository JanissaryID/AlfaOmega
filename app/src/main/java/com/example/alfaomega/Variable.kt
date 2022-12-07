package com.example.alfaomega

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.menu.MenuModel
import com.example.alfaomega.api.transaction.TransactionModel

//Variable New Transaction
var NEW_TRANSACATION_CUSTOMER: String by mutableStateOf("")
var NEW_TRANSACATION_MENU: String by mutableStateOf("")
var NEW_TRANSACATION_CLASS: Boolean by mutableStateOf(false)
var NEW_TRANSACATION_TYPE: String by mutableStateOf("")
var NEW_TRANSACATION_PRICE: String by mutableStateOf("")
var NEW_TRANSACATION_TIME: Int by mutableStateOf(0)
var NEW_TRANSACATION_IS_WASHER: Boolean by mutableStateOf(false)
var NEW_TRANSACATION_IS_DRYER: Boolean by mutableStateOf(false)
var NEW_TRANSACATION_BUTTON: Boolean by mutableStateOf(true)


//Variable Store
var STORE_NAME: String by mutableStateOf("Alfa Omega")
var STORE_CITY: String by mutableStateOf("Salatiga")
var STORE_ADDRESS: String by mutableStateOf("Jl. Pemuda Pancasila")
var STORE_ID: String by mutableStateOf("6385c1a3964c12ebc5e5fdb7")

//Variable Menu
var MENU_STATE: Int by mutableStateOf(0)
var MENU_LIST_TITAN_RESPONSE: ArrayList<MenuModel> by mutableStateOf(arrayListOf())
var MENU_LIST_GIANT_RESPONSE: ArrayList<MenuModel> by mutableStateOf(arrayListOf())
var MENU_ERROR_MESSAGE: String by mutableStateOf("")
var MENU_MACHINE_CLASS: Int by mutableStateOf(0)

//Variable Transaction Active
var TRANSACTION_ACTIVE_STATE: Int by mutableStateOf(0)
var TRANSACTION_ACTIVE_RESPONSE: ArrayList<TransactionModel> by mutableStateOf(arrayListOf())

//Variable Transaction
var TRANSACATION_CUSTOMER: String by mutableStateOf("")
var TRANSACATION_MENU: String by mutableStateOf("")
var TRANSACATION_CLASS: Boolean by mutableStateOf(false)
var TRANSACATION_TYPE: String by mutableStateOf("")
var TRANSACATION_PRICE: String by mutableStateOf("")
var TRANSACATION_PAYMENT: String by mutableStateOf("")
var TRANSACATION_DATE: String by mutableStateOf("")
var TRANSACATION_ADMIN: String by mutableStateOf("")
var TRANSACATION_ID: String by mutableStateOf("")
var TRANSACATION_TIME: Int by mutableStateOf(0)
var TRANSACATION_STATUS_MACHINE: Int by mutableStateOf(0)
var TRANSACATION_IS_WASHER: Boolean by mutableStateOf(false)
var TRANSACATION_IS_DRYER: Boolean by mutableStateOf(false)
var TRANSACATION_BUTTON: Boolean by mutableStateOf(true)

//Variable Screen
var TRANSACTION_SCREEN: Boolean by mutableStateOf(false)
var MACHINE_SCREEN: Boolean by mutableStateOf(false)

//Variable Machine
var LIST_MACHINE_DRYER_GIANT: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var LIST_MACHINE_WASHER_GIANT: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var LIST_MACHINE_DRYER_TITAN: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var LIST_MACHINE_WASHER_TITAN: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var MACHINE_STATE: Int by mutableStateOf(0)
var MACHINE_ERROR_MESSAGE: String by mutableStateOf("")
var TIME_WASHER_GIANT: Int by mutableStateOf(0)
var TIME_WASHER_TITAN: Int by mutableStateOf(0)
var TIME_DRYER_GIANT: Int by mutableStateOf(0)
var TIME_DRYER_TITAN: Int by mutableStateOf(0)
var MACHINE_ID: String by mutableStateOf("")
var MACHINE_CLASS: Boolean by mutableStateOf(false)
var MACHINE_TYPE: Boolean by mutableStateOf(false)
var MACHINE_TIME: Int by mutableStateOf(0)
var MACHINE_BUTTON_UPDATE: Boolean by mutableStateOf(true)