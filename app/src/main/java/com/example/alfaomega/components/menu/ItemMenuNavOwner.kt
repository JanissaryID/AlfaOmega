package com.example.alfaomega.components.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.navigations.Screens

@Composable
fun ItemMenuNavOwner(
    iconNav: Int,
    nameIcon: String,
    navMenu: String,
    navController: NavController
) {
    val colorFont = MaterialTheme.colorScheme.primary

    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(modifier = Modifier
            .wrapContentHeight()
            .width(92.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Surface(color = colorFont,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.wrapContentSize()) {
                Surface(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .size(42.dp)
                        .clickable {
                            navController.navigate(route = navMenu){
                                popUpTo(navMenu) {
                                    inclusive = true
                                }
                            }
                        }) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = iconNav),
                            contentDescription = nameIcon,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.surface
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = nameIcon,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}