package com.example.alfaomega.view.admin.machine

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.components.ItemMachine

@Composable
fun MachineLazyGrid(
    machineModel: List<MachineModel>,
    navController: NavController,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        itemsIndexed(items = machineModel){ index, machine ->
            ItemMachine(
                machineModel = machine,
                usedMachine = machine.machineStatus!!,
                index = if(selectedIndex != index) index else -1,
                selected = if(selectedIndex == index) false else true,
                onClick = onItemClick,
                navController = navController
            )
        }
    }
}