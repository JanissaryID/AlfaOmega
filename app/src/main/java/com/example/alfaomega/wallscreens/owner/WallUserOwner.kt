package com.example.alfaomega.wallscreens.owner

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.LIST_OWNER
import com.example.alfaomega.LIST_USER
import com.example.alfaomega.USER_STATE
import com.example.alfaomega.USER_TYPE
import com.example.alfaomega.view.owner.user.UserLoadData

@Composable
fun WallUserOwner(paddingValues: PaddingValues, navController: NavController) {
    Surface(color = Color.Transparent, modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserLoadData(
                userState = USER_STATE,
                user = if(USER_TYPE == 2) LIST_OWNER else LIST_USER,
                navController = navController)
        }
    }
}