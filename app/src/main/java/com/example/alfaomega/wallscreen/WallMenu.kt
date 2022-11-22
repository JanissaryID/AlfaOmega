package com.example.alfaomega.wallscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.alfaomega.component.ComponentMenu
import com.example.alfaomega.component.ComponentTransaction

@Composable
fun WallMenu(paddingValues: PaddingValues) {
    val numbers = remember {
        mutableStateListOf(1,2,3,4,5,6,7,8,9,10)
    }
    Spacer(modifier = Modifier.width(8.dp))
    Surface(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Giant",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Surface(
                        modifier = Modifier.size(width = 70.dp, height = 4.dp),
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(100)
                    ) {

                    }
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Titan",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Surface(
                        modifier = Modifier.size(width = 70.dp, height = 4.dp),
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(100)
                    ) {

                    }
                }
            }
            LazyColumn(
                modifier = Modifier.padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(items = numbers, key = {it.hashCode()}) {
                    ComponentMenu(
                        menuTitle = "Cuci-Kering",
                        menuType = "Giant 8 Kg",
                        menuPrice = "16000",
                        menuTime = "45 Menit"
                    )
                }
            }
        }
    }
}