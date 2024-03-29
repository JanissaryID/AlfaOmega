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
    object Bluetooth: Screens(route = "bluetooth_screen")
    object ReportMachine: Screens(route = "report_machine_screen")
    object ExpensesStore: Screens(route = "expenses_store_screen")

    object MenuOwner: Screens(route = "menu_owner_screen")
    object MenuEditOwner: Screens(route = "menu_edit_owner_screen")
    object TransactionOwner: Screens(route = "transcation_owner_screen")
    object RulesOwner: Screens(route = "rules_screen")
    object RulesEditOwner: Screens(route = "rules_edit_owner_screen")
    object UserOwner: Screens(route = "user_screen")
    object UserEditOwner: Screens(route = "user_edit_owner_screen")
    object OutletOwner: Screens(route = "outlet_owner_screen")
    object MachineOwner: Screens(route = "machine_owner_screen")
    object ExpensesOwner: Screens(route = "expenses_owner_screen")
    object QrOwner: Screens(route = "qr_owner_screen")

    object HomeDeveloper: Screens(route = "developer_screen")
    object OwnerListDeveloper: Screens(route = "owner_list_developer_screen")
    object OwnerEditStoreDeveloper: Screens(route = "owner_edit_store_developer_screen")
}