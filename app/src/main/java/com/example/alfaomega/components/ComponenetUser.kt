package com.example.alfaomega.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens

@Composable
fun ComponentUser(
    name: String,
    password: String,
    idUser: String,
    navController: NavController
) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(ROUND_CORNER.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(ROUND_CORNER.dp),
            modifier = Modifier.clickable {
                if(USER_TYPE == 1) {
                    EDIT_MODE = true
                    USER_NAME_EDIT = name
                    USER_PASSWORD_EDIT = password
                    USER_SCREEN_TYPE = true
                    ID_USER_EDIT = idUser
                    navController.navigate(route = Screens.UserEditOwner.route)
                }
                else if(USER_TYPE == 2){
                    OWNER_ID = idUser
                    navController.navigate(route = Screens.Store.route){
                        popUpTo(Screens.Store.route) {
                            inclusive = true
                        }
                    }
                }
            }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
            ) {
                Surface(
                    border = BorderStroke(3.dp, MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(100)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_twotone_person_24),
                        contentDescription = "Icon User",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(13.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = name.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.surface
                    )
                    if(USER_TYPE == 2){
                        ButtonView(
                            title = "Edit",
                            enable = true,
                            modifier = Modifier.width(90.dp)
                        ) {
                            EDIT_MODE = true
                            USER_NAME_EDIT = name
                            USER_PASSWORD_EDIT = password
                            USER_SCREEN_TYPE = true
                            ID_USER_EDIT = idUser
                            navController.navigate(route = Screens.UserEditOwner.route){
                                popUpTo(Screens.UserEditOwner.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}