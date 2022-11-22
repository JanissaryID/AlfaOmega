package com.example.alfaomega.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.alfaomega.R
import com.example.alfaomega.wallscreen.WallHome
import com.example.alfaomega.wallscreen.WallPicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    typeScreen: Boolean,
    tittleScreen: String,
    wallScreen: Int
) {
    if (typeScreen){
        Scaffold(topBar = {
            SmallTopBar(
                typeScreen = typeScreen,
                tittleScreen = tittleScreen)
        },
            content = {
//            WallHome(paddingValues = it)
                WallPicker(
                    wallScreen = wallScreen,
                    paddingValues = it
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.fluent_add_12_filled),
                        contentDescription = "Icon Add",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )
    }
    else{
        Scaffold(topBar = {
            SmallTopBar(
                typeScreen = typeScreen,
                tittleScreen = tittleScreen)
        },
            content = {
//            WallHome(paddingValues = it)
                WallPicker(
                    wallScreen = wallScreen,
                    paddingValues = it
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopBar(
    typeScreen: Boolean,
    tittleScreen: String
) {
    if(typeScreen){
        SmallTopAppBar(
            title = {Text(
                text = tittleScreen,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )},
            actions = {
                Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                        contentDescription = "Icon TImer",
                        modifier = Modifier.clickable {  }
                    )
                }
            }
        )
    }
    else{
        SmallTopAppBar(
            title = {Text(
                text = tittleScreen,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )},
            navigationIcon = {
                Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = "Icon TImer",
                        modifier = Modifier.clickable {  }
                    )
                }
            }
        )
    }
}