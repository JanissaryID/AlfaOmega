package com.example.alfaomega

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.alfaomega.api.expenses.ExpensesModel
import com.example.alfaomega.api.income.IncomeModel
import com.example.alfaomega.api.log.LogModel
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.menu.MenuModel
import com.example.alfaomega.api.problem.ProblemModel
import com.example.alfaomega.api.qr.QrModel
import com.example.alfaomega.api.rules.RuleModel
import com.example.alfaomega.api.store.StoreModel
import com.example.alfaomega.api.transaction.TransactionModel
import com.example.alfaomega.api.user.UserModel
import com.madrapps.plot.line.DataPoint

//Variable New Transaction
var NEW_TRANSACATION_CUSTOMER: String by mutableStateOf("")
var NEW_TRANSACATION_PHONE: String by mutableStateOf("")
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
var STORE_PHONE: String by mutableStateOf("")
var STORE_ID: String by mutableStateOf("")
var STORE_LIST_RESPONSE: ArrayList<StoreModel> by mutableStateOf(arrayListOf())
var STORE_CODE_ERROR: Boolean by mutableStateOf(false)
var STORE_CODE_ERROR_STR: String by mutableStateOf("")

//var OUTLET_LIST: ArrayList<StoreModel> by mutableStateOf(arrayListOf())
var STORE_STATE: Int by mutableStateOf(0)
var STORE_ERROR_MESSAGE: String by mutableStateOf("")
var STORE_ADMIN: String by mutableStateOf("")

//Variable Menu
var MENU_STATE_GIANT: Int by mutableStateOf(0)
var MENU_STATE_TITAN: Int by mutableStateOf(0)
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
var TRANSACTION_NUMBER: String by mutableStateOf("")
var TRANSACTION_MONEY: String by mutableStateOf("")

//Variable Screen
var TRANSACTION_SCREEN: Boolean by mutableStateOf(false)
var MACHINE_SCREEN: Boolean by mutableStateOf(false)
var SCREEN_ACTIVE_NOW: String by mutableStateOf("")

//Variable Machine
var LIST_MACHINE_DRYER_GIANT: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var LIST_MACHINE_WASHER_GIANT: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var LIST_MACHINE_DRYER_TITAN: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var LIST_MACHINE_WASHER_TITAN: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var LIST_MACHINE: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
var MACHINE_STATE: Int by mutableStateOf(0)
var MACHINE_ERROR_MESSAGE: String by mutableStateOf("")
var MACHINE_ID: String by mutableStateOf("")
var MACHINE_CLASS: Boolean by mutableStateOf(false)
var MACHINE_TYPE: Boolean by mutableStateOf(false)
var MACHINE_NUMBER: Int by mutableStateOf(0)
var MACHINE_MAC: String by mutableStateOf("")
var MACHINE_BUTTON_UPDATE: Boolean by mutableStateOf(false)
var MACHINE_LOADING: Boolean by mutableStateOf(false)

//Variable user
var USER_STATE: Int by mutableStateOf(0)
var USER_TYPE: Int by mutableStateOf(0)
var USER_NAME: String by mutableStateOf("")
var USER_ERROR_MESSAGE: String by mutableStateOf("")
var BUTTON_LOGIN: Boolean by mutableStateOf(true)
var BUTTON_LOGIN_CLICKED: Boolean by mutableStateOf(false)
var FAILED_LOGIN: Boolean by mutableStateOf(false)
var FAILED_LOGIN_EMPTY: Boolean by mutableStateOf(false)
var FAILED_LOGIN_ALREADY: Boolean by mutableStateOf(false)
var STATUS_LOGIN_LOGOUT: Boolean by mutableStateOf(false)
var LIST_USER: ArrayList<UserModel> by mutableStateOf(arrayListOf())

//Variable ownerMenu
var EDIT_MODE: Boolean by mutableStateOf(false)

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

//Variable User
var USER_NAME_EDIT: String by mutableStateOf("")
var USER_PASSWORD_EDIT: String by mutableStateOf("")
var USER_SCREEN_TYPE: Boolean by mutableStateOf(false)
var ID_USER_EDIT: String by mutableStateOf("")
var BUTTON_USER_EDIT: Boolean by mutableStateOf(false)

//Variable Transaction Owner
var DATE_SCREEN_TYPE: Boolean by mutableStateOf(false)
var DATE_PICK: String by mutableStateOf("")
var LOG_STATE: Int by mutableStateOf(0)
var LOG_ERROR_MESSAGE: String by mutableStateOf("")
var LIST_LOG: ArrayList<LogModel> by mutableStateOf(arrayListOf())

//Variable Bluetooth
// UUID 00000000-0000-1000-8000-00805f9b34fb
var ADDRESS_DEVICE: String by mutableStateOf("")
var UUID_DEVICE: String by mutableStateOf("")
var BLUETOOTH_STATE: Int by mutableStateOf(0)
var BLUETOOTH_STATE_ON: Boolean by mutableStateOf(false)
var MY_CONTEXT : ComponentActivity? = null
var STAT_BLUETOOTH_CONNECT: Boolean by mutableStateOf(false)

//Variable Problem Machine
var LIST_PROBLEM_MACHINE: ArrayList<ProblemModel> by mutableStateOf(arrayListOf())
var PROBLEM_MACHINE_STATE: Int by mutableStateOf(0)
var PROBLEM_MACHINE_ERROR_MESSAGE: String by mutableStateOf("")
var PROBLEM_MACHINE_STATE_SCREEN: Boolean by mutableStateOf(false)

//Variable API
var KEY_API: String by mutableStateOf("")
var TOKEN_API: String by mutableStateOf("")
var URL_SERVER: String by mutableStateOf("https://api.v2.kontenbase.com/query/api/v1/")

//Variable Developer Mode
var LIST_OWNER: ArrayList<UserModel> by mutableStateOf(arrayListOf())
var INDEX_MENU_DEV: Int by mutableStateOf(0)

//Variable Owner Mode
var OWNER_ID: String by mutableStateOf("")
var USER_ID: String by mutableStateOf("")

//Variable Store Edit
var STORE_NAME_EDIT: String by mutableStateOf("")
var STORE_ADDRESS_EDIT: String by mutableStateOf("")
var STORE_CITY_EDIT: String by mutableStateOf("")

//Variable Style
val ROUND_CORNER: Int = 24

//Variable Graph Income
var LIST_INCOME: ArrayList<IncomeModel> by mutableStateOf(arrayListOf())
var LIST_INCOME_FLOAT: ArrayList<DataPoint> by mutableStateOf(arrayListOf())
var LIST_EXPENSES_FLOAT: ArrayList<DataPoint> by mutableStateOf(arrayListOf())
var LIST_PROFIT_FLOAT: ArrayList<DataPoint> by mutableStateOf(arrayListOf())
var INCOME_STATE: Int by mutableStateOf(0)
var INCOME_SUM: Int by mutableStateOf(0)
var EXPENSES_SUM: Int by mutableStateOf(0)
var PROFIT_SUM: Int by mutableStateOf(0)

var LIST_INCOME_STORE: ArrayList<IncomeModel> by mutableStateOf(arrayListOf())
var LIST_INCOME_FLOAT_STORE: ArrayList<DataPoint> by mutableStateOf(arrayListOf())
var LIST_EXPENSES_FLOAT_STORE: ArrayList<DataPoint> by mutableStateOf(arrayListOf())
var LIST_PROFIT_FLOAT_STORE: ArrayList<DataPoint> by mutableStateOf(arrayListOf())
var INCOME_STATE_STORE: Int by mutableStateOf(0)
var INCOME_SUM_STORE: Int by mutableStateOf(0)
var EXPENSES_SUM_STORE: Int by mutableStateOf(0)
var PROFIT_SUM_STORE: Int by mutableStateOf(0)

var INCOME_ERROR_MESSAGE: String by mutableStateOf("")

//var STAT_GET_DATA: Boolean by mutableStateOf(false)
var ONE_RENDER_GRAPH: Boolean by mutableStateOf(false)

//Variable Expenses
var LIST_EXPENSES: ArrayList<ExpensesModel> by mutableStateOf(arrayListOf())
var EXPENSES_STATE: Int by mutableStateOf(0)
var LIST_EXPENSES_STORE: ArrayList<ExpensesModel> by mutableStateOf(arrayListOf())
var EXPENSES_STATE_STORE: Int by mutableStateOf(0)
var EXPENSES_ERROR_MESSAGE: String by mutableStateOf("")

//Variable Qr
var QR_DATA: QrModel by mutableStateOf(QrModel())
var QR_STATE: Int by mutableStateOf(0)
var QR_ERROR_MESSAGE: String by mutableStateOf("")
//var QR_ID: String by mutableStateOf("")