package com.example.alfaomega.wallscreens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.components.ComponentMenuClass
import com.example.alfaomega.view.admin.menu.MenuLoadData

@Composable
fun WallMenu(
    paddingValues: PaddingValues,
    navController: NavController,
) {
//    val onItemClick = { index: Int -> selectedIndex = index}
    val selectionMenuClass = listOf("Giant", "Titan")
    var selected_index_class by remember {mutableStateOf(MENU_MACHINE_CLASS)}
    val on_click_index_class = { index: Int -> selected_index_class = index}

    Spacer(modifier = Modifier.width(8.dp))
    Surface(color = Color.Transparent, modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ){
                itemsIndexed(items = selectionMenuClass){index, menu ->
                    ComponentMenuClass(
                        titleMenu = menu,
                        index = if(selected_index_class != index){
                            index
                        }  else index,
                        onClick = on_click_index_class,
                        selected = if(selected_index_class == index) false else true
                    )
                }
            }
            MenuLoadData(
                navController = navController,
                menu = if (selected_index_class == 0) MENU_LIST_GIANT_RESPONSE else MENU_LIST_TITAN_RESPONSE,
                menuState = MENU_STATE,
                selectedIndex = selected_index_class
            )
        }
    }
}