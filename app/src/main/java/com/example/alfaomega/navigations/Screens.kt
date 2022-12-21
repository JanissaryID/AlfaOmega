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

    object MenuOwner: Screens(route = "menu_owner_screen")
    object MenuEditOwner: Screens(route = "menu_edit_owner_screen")
    object TransactionOwner: Screens(route = "transcation_owner_screen")
    object RulesOwner: Screens(route = "rules_screen")
    object RulesEditOwner: Screens(route = "rules_edit_owner_screen")
    object UserOwner: Screens(route = "user_screen")
    object UserEditOwner: Screens(route = "user_edit_owner_screen")

    object HomeDeveloper: Screens(route = "developer_screen")
}