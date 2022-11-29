package com.example.alfaomega

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.alfaomega.api.menu.MenuModel

//Variable New Transaction
var NEW_TRANSACATION_CUSTOMER: String by mutableStateOf("")
var NEW_TRANSACATION_MENU: String by mutableStateOf("")
var NEW_TRANSACATION_TYPE: String by mutableStateOf("")
var NEW_TRANSACATION_PRICE: String by mutableStateOf("")
var NEW_TRANSACATION_TIME: Int by mutableStateOf(0)

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