package com.example.alfaomega.wallscreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.wallscreens.admin.WallBluetooth
import com.example.alfaomega.wallscreens.admin.WallExpenses
import com.example.alfaomega.wallscreens.admin.WallReportMachine
import com.example.alfaomega.wallscreens.developer.WallHomeDeveloper
import com.example.alfaomega.wallscreens.developer.WallStoreEditDeveloper
import com.example.alfaomega.wallscreens.firstwall.WallFirstAdminLogin
import com.example.alfaomega.wallscreens.firstwall.WallFirstOwner
import com.example.alfaomega.wallscreens.owner.*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WallPicker(
    wallScreen: Int,
    paddingValues: PaddingValues,
    navController: NavController,
    menuViewModel: MenuViewModel,
    protoViewModel: ProtoViewModel,
    bluetoothViewModel: BluetoothViewModel
) {
    when(wallScreen){
        0 -> WallHome(paddingValues = paddingValues, navController = navController)
        1 -> WallMenu(paddingValues = paddingValues, navController = navController)
        2 -> WallDetailTransaction(paddingValues = paddingValues, navController = navController, transactionViewModel = TransactionViewModel())
        3 -> WallStore(navController = navController, paddingValues = paddingValues, protoViewModel = protoViewModel)
        4 -> WallMachine(paddingValues = paddingValues, navController = navController)
        5 -> WallStoreList(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel, storeViewModel = StoreViewModel())
        6 -> WallTransactionList(paddingValues = paddingValues, navController = navController)
        7 -> WallLogin(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel, userViewModel = UserViewModel())
        8 -> WallHomeOwnerV2(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel)
        9 -> WallMenuEditOwner(paddingValues = paddingValues, navController = navController, menuViewModel = menuViewModel)
        10 -> WallRulesOwner(paddingValues = paddingValues, navController = navController)
        11 -> WallUserOwner(paddingValues = paddingValues, navController = navController)
        12 -> WallRulesEditOwner(paddingValues = paddingValues, navController = navController, ruleViewModel = RuleViewModel())
        13 -> WallUserEditOwner(paddingValues = paddingValues, navController = navController, userViewModel = UserViewModel())
        20 -> WallFirstOwner(navController = navController, paddingValues = paddingValues, protoViewModel = protoViewModel)
        21 -> WallFirstAdminLogin(paddingValues = paddingValues)
        22 -> WallTransactionOwner(paddingValues = paddingValues, navController = navController)
        23 -> WallBluetooth(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        24 -> WallReportMachine(paddingValues = paddingValues, navController = navController)
        25 -> WallOutletOwner(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel)
        26 -> WallExpenses(paddingValues = paddingValues, navController = navController)
        29 -> WallHomeDeveloper(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel)
        30 -> WallStoreEditDeveloper(paddingValues = paddingValues, navController = navController, storeViewModel = StoreViewModel())
        else -> print("Opps tidak ada")
    }
}