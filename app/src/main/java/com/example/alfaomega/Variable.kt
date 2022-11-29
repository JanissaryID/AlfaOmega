package com.example.alfaomega

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

var NAME_CUSTOMER_NEW_TRANSACATION: String by mutableStateOf("")
var TITLE_NEW_TRANSACATION: String by mutableStateOf("")
var TYPE_NEW_TRANSACATION: String by mutableStateOf("Giant 8 Kg")
var PRICE_NEW_TRANSACATION: String by mutableStateOf("25000")

var STORE_NAME: String by mutableStateOf("Alfa Omega")
var STORE_CITY: String by mutableStateOf("Salatiga")
var STORE_ADDRESS: String by mutableStateOf("Jl. Pemuda Pancasila")