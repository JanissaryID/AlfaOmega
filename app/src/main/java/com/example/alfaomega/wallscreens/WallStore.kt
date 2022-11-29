package com.example.alfaomega.wallscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.STORE_ADDRESS
import com.example.alfaomega.STORE_CITY
import com.example.alfaomega.STORE_NAME
import com.example.alfaomega.components.ComponentTransaction
import com.example.alfaomega.components.StoreMenuItem

@Composable
fun WallStore(
    navController: NavController,
    paddingValues: PaddingValues,
//    storeName: String,
//    storeCity: String,
//    storeAddress: String
) {

    val numbers = remember {
        mutableStateListOf(1,2,3)
    }

    Surface(modifier = Modifier
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
                shape = RoundedCornerShape(100)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_storefront_24),
                        contentDescription = "Icon TImer",
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = STORE_NAME,
                fontWeight = FontWeight.ExtraBold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = STORE_CITY,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            )
            Spacer(modifier = Modifier.height(0.dp))
            Text(
                text = STORE_ADDRESS,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                LazyColumn(modifier = Modifier.wrapContentSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ){
                    items(items = numbers, key = {it.hashCode()}) {
                        StoreMenuItem()
                    }
                }
            }
        }
    }
}