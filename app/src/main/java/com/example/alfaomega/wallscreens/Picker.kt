package com.example.alfaomega.wallscreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.wallscreens.developer.WallHomeDeveloper
import com.example.alfaomega.wallscreens.firstwall.WallFirstOwner
import com.example.alfaomega.wallscreens.owner.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WallPicker(
    wallScreen: Int,
    paddingValues: PaddingValues,
    navController: NavController,
    menuViewModel: MenuViewModel,
    protoViewModel: ProtoViewModel
) {
    when(wallScreen){
        0 -> WallHome(paddingValues = paddingValues, navController = navController)
        1 -> WallMenu(paddingValues = paddingValues, navController = navController)
        2 -> WallDetailTransaction(paddingValues = paddingValues, navController = navController, transactionViewModel = TransactionViewModel())
        3 -> WallStore(navController = navController, paddingValues = paddingValues, protoViewModel = protoViewModel)
        4 -> WallMachine(paddingValues = paddingValues, navController = navController)
        5 -> WallStoreList(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel)
        6 -> WallTransactionList(paddingValues = paddingValues, navController = navController)
        7 -> WallLogin(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel, userViewModel = UserViewModel())
        8 -> WallHomeOwner(paddingValues = paddingValues, navController = navController, protoViewModel = protoViewModel)
        9 -> WallMenuEditOwner(paddingValues = paddingValues, navController = navController, menuViewModel = menuViewModel)
        10 -> WallRulesOwner(paddingValues = paddingValues, navController = navController)
        11 -> WallUserOwner(paddingValues = paddingValues, navController = navController)
        12 -> WallRulesEditOwner(paddingValues = paddingValues, navController = navController, ruleViewModel = RuleViewModel())
        13 -> WallUserEditOwner(paddingValues = paddingValues, navController = navController, userViewModel = UserViewModel())
        20 -> WallFirstOwner(navController = navController, paddingValues = paddingValues)
        29 -> WallHomeDeveloper()
        else -> print("Opps tidak ada")
    }
}