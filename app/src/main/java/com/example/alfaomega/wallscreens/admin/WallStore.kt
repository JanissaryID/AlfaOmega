package com.example.alfaomega.wallscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.alfaomega.R
import com.example.alfaomega.STORE_ADDRESS
import com.example.alfaomega.STORE_CITY
import com.example.alfaomega.STORE_NAME
import com.example.alfaomega.`object`.store.MenuStore
import com.example.alfaomega.`object`.store.MenuStoreModel
import com.example.alfaomega.components.ItemStoreMenu
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun WallStore(
    navController: NavController,
    paddingValues: PaddingValues,
    protoViewModel: ProtoViewModel
) {
    var list: ArrayList<MenuStoreModel> = arrayListOf()
    list.addAll(MenuStore.listData)

    Surface(
        color = Color.Transparent,
        modifier = Modifier
        .fillMaxSize()
        .padding(top = paddingValues.calculateTopPadding())) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .size(144.dp),
                shape = RoundedCornerShape(100),
            ) {
                Surface(
                    shape = RoundedCornerShape(100),
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screens.Store.route)
                    },
                    color = Color.Transparent
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_twotone_storefront_24),
                            contentDescription = "Icon TImer",
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = STORE_NAME,
                fontWeight = FontWeight.ExtraBold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = STORE_CITY,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(0.dp))
            Text(
                text = STORE_ADDRESS,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                LazyColumn(modifier = Modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ){
                    items(items = list) { menu ->
                        ItemStoreMenu(
                            title = menu.titleName,
                            subTitle = menu.subTitle,
                            iconMenu = menu.menuIcon,
                            screenMenuItem = menu.screensMenu,
                            typeMenu = menu.typeMenu,
                            navController = navController,
                            protoViewModel = protoViewModel
                        )
                    }
                }
            }
        }
    }
}