package com.example.alfaomega.view.admin.expenses

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.api.expenses.ExpensesModel
import com.example.alfaomega.components.item.ItemExpenses
import com.example.alfaomega.proto.ProtoViewModel

@SuppressLint("MissingPermission")
@Composable
fun ExpensesLazyColumn(
    expenses: List<ExpensesModel>,
//    protoViewModel: ProtoViewModel,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 0.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ){
        itemsIndexed(items = expenses) { index, expenses ->

            ItemExpenses(
                nameAdmin = expenses.admin!!,
                date = expenses.date!!,
                nominal = expenses.expenses!!,
                note = expenses.notes!!,
            )
            Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
//            Log.d("get_log", "Device = ${device.name} uuid = ${if(device.name == "RPP02N") "${device.uuids[1].toString()}" else ""}")
        }
    }
}