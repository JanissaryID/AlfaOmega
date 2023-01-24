package com.example.alfaomega.wallscreens.firstwall

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.components.menu.ItemMenuNavOwner
import com.example.alfaomega.components.store_item.ItemStoreSquare
import com.example.alfaomega.`object`.owner.MenuOwner
import com.example.alfaomega.`object`.owner.MenuOwnerModel
import com.example.alfaomega.graph.SampleLineGraph
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.owner.store.StoreLoadDataOwner
import com.madrapps.plot.line.DataPoint

@Composable
fun WallFirstOwner(
    navController: NavController,
    paddingValues: PaddingValues,
    protoViewModel: ProtoViewModel
) {
    val colorFont = MaterialTheme.colorScheme.surface
    var list: ArrayList<MenuOwnerModel> = arrayListOf()
    list.addAll(MenuOwner.listData)

    val selectionMenuTitle = listOf(
        stringResource(R.string.RuleTitle),
        stringResource(R.string.Menu),
        stringResource(R.string.EmployeeTitle),
    )

    val dataList: List<DataPoint> = listOf(
        DataPoint(0f, 0f),
        DataPoint(1f, 0f),
        DataPoint(2f, 25f),
        DataPoint(3f, 75f),
        DataPoint(4f, 100f),
        DataPoint(5f, 80f),
        DataPoint(6f, 75f),
        DataPoint(7f, 50f),
        DataPoint(8f, 80f),
        DataPoint(9f, 70f),
        DataPoint(10f, 0f),
        DataPoint(11f, 0f),
        DataPoint(12f, 45f),
        DataPoint(13f, 20f),
        DataPoint(14f, 40f),
        DataPoint(15f, 75f),
        DataPoint(16f, 50f),
        DataPoint(17f, 75f),
        DataPoint(18f, 40f),
        DataPoint(19f, 20f),
        DataPoint(20f, 0f),
        DataPoint(21f, 0f),
        DataPoint(22f, 50f),
        DataPoint(23f, 25f),
    )

    Column(modifier = Modifier.padding(
        top = paddingValues.calculateTopPadding(),
        bottom = paddingValues.calculateBottomPadding()
    )) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                Text(
                    text = "Hello, Owner",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    color = colorFont
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Finance in this month",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = colorFont.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Net profit Rp. 7.478.000",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = colorFont.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color =  MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(28.dp),
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp, top = 12.dp, bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Income",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Rp. 7.860.000",
                                fontWeight = FontWeight.Bold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                            )
                        }

                        Divider(modifier = Modifier
                            .height(42.dp)
                            .width(2.dp), thickness = 2.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))

                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Outcome",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Rp. 382.000",
                                fontWeight = FontWeight.Bold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(12.dp))
            Surface(color = Color.Transparent,
            ) {
                Text(
                    text = "Menu",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(ROUND_CORNER.dp),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ){
                        itemsIndexed(items = list) { index, menuNav ->
                            ItemMenuNavOwner(
                                iconNav = menuNav.menuIcon,
                                nameIcon = selectionMenuTitle[menuNav.titleName],
                                navMenu = menuNav.screensMenu)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Surface(color = Color.Transparent,
            ) {
                Text(
                    text = "Outlet",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp
                    ),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StoreLoadDataOwner(
                        storeState = STORE_STATE,
                        store = STORE_LIST_RESPONSE,
                        navController = navController,
                        protoViewModel = protoViewModel
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Surface(color = Color.Transparent,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()) {
                    Text(
                        text = "Graph Finance",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(ROUND_CORNER.dp),
                    ){
                        SampleLineGraph(dataList)
                    }
                }
            }
        }
    }
}