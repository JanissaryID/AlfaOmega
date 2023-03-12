package com.example.alfaomega.wallscreens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.components.ComponentMenuClass
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.admin.menu.MenuLoadData

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WallMenu(
    paddingValues: PaddingValues,
    navController: NavController,
    protoViewModel: ProtoViewModel
) {
//    val onItemClick = { index: Int -> selectedIndex = index}
    val selectionMenuClass = listOf(stringResource(R.string.MenuGiant), stringResource(R.string.MenuTitan))
    var selected_index_class by remember {mutableStateOf(MENU_MACHINE_CLASS)}
    val on_click_index_class = { index: Int -> selected_index_class = index
                                                MENU_MACHINE_CLASS = index }

//    Spacer(modifier = Modifier.width(8.dp))
    Surface(color = Color.Transparent, modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
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
            Spacer(modifier = Modifier.height(16.dp))
            MenuLoadData(
                navController = navController,
                menu = if (selected_index_class == 0) MENU_LIST_GIANT_RESPONSE else MENU_LIST_TITAN_RESPONSE,
                menuState = if (selected_index_class == 0) MENU_STATE_GIANT else MENU_STATE_TITAN,
                selectedIndex = selected_index_class,
                protoViewModel = protoViewModel
            )
        }
    }
}