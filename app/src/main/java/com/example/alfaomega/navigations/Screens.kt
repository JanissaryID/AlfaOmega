package com.example.alfaomega.navigations

sealed class Screens(val route: String){
    object Home: Screens(route = "home_screen")
    object Menu: Screens(route = "menu_screen")
    object DetailTransaction: Screens(route = "detail_transaction_screen")
    object StoreProfile: Screens(route = "store_profile")
    object Machine: Screens(route = "machine_screen")
    object Store: Screens(route = "store_screen")
    object TransactionList: Screens(route = "list_transaction_screen")
    object Login: Screens(route = "login_screen")

    object HomeOwner: Screens(route = "owner_screen")

    object HomeDeveloper: Screens(route = "developer_screen")
}