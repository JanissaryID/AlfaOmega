package com.example.alfaomega

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.alfaomega.api.menu.MenuModel

var NAME_CUSTOMER_NEW_TRANSACATION: String by mutableStateOf("")
var TITLE_NEW_TRANSACATION: String by mutableStateOf("")
var TYPE_NEW_TRANSACATION: String by mutableStateOf("Giant 8 Kg")
var PRICE_NEW_TRANSACATION: String by mutableStateOf("25000")

var STORE_NAME: String by mutableStateOf("Alfa Omega")
var STORE_CITY: String by mutableStateOf("Salatiga")
var STORE_ADDRESS: String by mutableStateOf("Jl. Pemuda Pancasila")
var STORE_ID: String by mutableStateOf("6385c1a3964c12ebc5e5fdb7")

var MENU_STATE: Int by mutableStateOf(0)
var MENU_LIST_RESPONSE: ArrayList<MenuModel> by mutableStateOf(arrayListOf())
var MENU_ERROR_MESSAGE: String by mutableStateOf("")
var MENU_MACHINE_CLASS: Int by mutableStateOf(0)