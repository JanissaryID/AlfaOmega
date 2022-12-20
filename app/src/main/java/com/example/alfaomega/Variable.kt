package com.example.alfaomega

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.menu.MenuModel
import com.example.alfaomega.api.rules.RuleModel
import com.example.alfaomega.api.store.StoreModel
import com.example.alfaomega.api.transaction.TransactionModel
import com.example.alfaomega.api.user.UserModel

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
var STORE_NAME: String by mutableStateOf("")
var STORE_CITY: String by mutableStateOf("")
var STORE_ADDRESS: String by mutableStateOf("")
//var STORE_ID: String by mutableStateOf("6385c1a3964c12ebc5e5fdb7")
var STORE_ID: String by mutableStateOf("")
var STORE_LIST_RESPONSE: ArrayList<StoreModel> by mutableStateOf(arrayListOf())
var STORE_STATE: Int by mutableStateOf(0)
var STORE_ERROR_MESSAGE: String by mutableStateOf("")

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
var TRANSACATION_STATUS_MACHINE: Int by mutableStateOf(0)
var TRANSACATION_IS_WASHER: Boolean by mutableStateOf(false)
var TRANSACATION_IS_DRYER: Boolean by mutableStateOf(false)
var TRANSACTION_ERROR: String by mutableStateOf("")
var TRANSACTION_RESPONSE: ArrayList<TransactionModel> by mutableStateOf(arrayListOf())
var TRANSACTION_STATE: Int by mutableStateOf(0)

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

//Variable user
var USER_STATE: Int by mutableStateOf(0)
var USER_TYPE: Int by mutableStateOf(0)
var USER_NAME: String by mutableStateOf("")
var USER_ERROR_MESSAGE: String by mutableStateOf("")
var BUTTON_LOGIN: Boolean by mutableStateOf(true)
var BUTTON_LOGIN_CLICKED: Boolean by mutableStateOf(false)
var FAILED_LOGIN: Boolean by mutableStateOf(false)
var LIST_USER: ArrayList<UserModel> by mutableStateOf(arrayListOf())

//Variable ownerMenu
var EDIT_MODE: Boolean by mutableStateOf(true)

//Variable Edit menu
var ID_MENU_EDIT: String by mutableStateOf("")
var TITLE_MENU_EDIT: String by mutableStateOf("")
var PRICE_MENU_EDIT: String by mutableStateOf("")
var CLASS_MENU_EDIT: Boolean by mutableStateOf(false)
var CLASS_MENU_EDIT_STRING: String by mutableStateOf("")
var WASHER_MENU_EDIT: Boolean by mutableStateOf(false)
var DRYER_MENU_EDIT: Boolean by mutableStateOf(false)
var SERVICE_MENU_EDIT: Boolean by mutableStateOf(false)
var BUTTON_MENU_EDIT: Boolean by mutableStateOf(false)
var MENU_SCREEN_TYPE: Boolean by mutableStateOf(false)

//Variable Rule
var LIST_RULE: ArrayList<RuleModel> by mutableStateOf(arrayListOf())
var RULE_STATE: Int by mutableStateOf(0)
var RULE_ERROR_MESSAGE: String by mutableStateOf("")
var RULE_TEXT_EDIT: String by mutableStateOf("")
var RULES_SCREEN_TYPE: Boolean by mutableStateOf(false)
var ID_RULE_EDIT: String by mutableStateOf("")
var BUTTON_RULE_EDIT: Boolean by mutableStateOf(false)
