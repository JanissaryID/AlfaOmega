package com.example.alfaomega.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.USER_TYPE
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar3(
    tittleScreen: String,
    navController: NavController,
    route: String,
    icon: Int,
    description: String,
    protoViewModel: ProtoViewModel
) {
    SmallTopAppBar(
        title = {
            Text(
            text = tittleScreen,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        },
        actions = {
            Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = description,
                    modifier = Modifier.clickable {
                        if(USER_TYPE != 1){
                            protoViewModel.updateNameUser(Nameuser = "")
                            protoViewModel.updateTypeUser(TypeUser = 1)
                            navController.navigate(route = route)
                        }
                        else{
                            navController.navigate(route = route)
                        }
                    }
                )
            }
        }
    )
}