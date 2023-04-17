package com.example.alfaomega.wallscreens.owner

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.components.ItemFinance
import com.example.alfaomega.components.menu.ItemMenuNavOwner
import com.example.alfaomega.datapoint.DataPoints
import com.example.alfaomega.graph.SampleLineGraph
import com.example.alfaomega.`object`.owner.MenuOwner
import com.example.alfaomega.`object`.owner.MenuOwnerModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.owner.store.StoreLoadDataOwner
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun WallHomeOwnerV2(
    navController: NavController,
    paddingValues: PaddingValues,
    protoViewModel: ProtoViewModel
) {
    val colorFont = MaterialTheme.colorScheme.surface
    var list: ArrayList<MenuOwnerModel> = arrayListOf()
    list.addAll(MenuOwner.listData)

    val selectionMenuTitle = listOf(
        stringResource(R.string.RulesTitle),
        stringResource(R.string.QrPayment),
        stringResource(R.string.EmployeeTitle),
    )

    val selectionFinanceTitle = listOf(
        stringResource(R.string.IncomeTitle),
        stringResource(R.string.ExpensexTitle),
        stringResource(R.string.NetProfitTitle),
    )

    val selectionFinanceGraphTitle = listOf(
        stringResource(R.string.AllTitle),
        stringResource(R.string.IncomeTitle),
        stringResource(R.string.ExpensexTitle),
        stringResource(R.string.NetProfitTitle),
    )

    var selectedItem by remember {
        mutableStateOf(0)
    }

    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.setMaximumFractionDigits(0)

    Column(modifier = Modifier.padding(
        top = paddingValues.calculateTopPadding(),
        bottom = paddingValues.calculateBottomPadding()
    )) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
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
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = colorFont.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color =  MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        contentPadding = PaddingValues(horizontal = 22.dp)
                    ){
                        itemsIndexed(items = selectionFinanceTitle) { index, item ->
                            if(index == 0){
                                ItemFinance(title = item, price = "Rp. ${numberFormat.format(
                                    INCOME_SUM).substring(2, numberFormat.format(INCOME_SUM).length)}", colorFont = MaterialTheme.colorScheme.primary)
                            }
                            else if(index == 1){
                                ItemFinance(title = item, price = "Rp. ${numberFormat.format(
                                    EXPENSES_SUM).substring(2, numberFormat.format(EXPENSES_SUM).length)}", colorFont = MaterialTheme.colorScheme.error)
                            }
                            else{
                                ItemFinance(title = item, price =
                                    if(PROFIT_SUM < 0){
                                        "-Rp. ${numberFormat.format(
                                            (PROFIT_SUM * -1)).substring(2, numberFormat.format((PROFIT_SUM * -1)).length)}"
                                    }
                                    else{
                                        "Rp. ${numberFormat.format(
                                            PROFIT_SUM).substring(2, numberFormat.format(PROFIT_SUM).length)}"
                                    }
                                    , colorFont = MaterialTheme.colorScheme.onPrimary)
                            }
                            if (index < selectionFinanceTitle.lastIndex){
                                Spacer(modifier = Modifier.width(16.dp))
                                Divider(modifier = Modifier
                                    .height(42.dp)
                                    .width(2.dp), thickness = 2.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                }
            }
        }
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)) {
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
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp)
                        .fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp, horizontal = 8.dp)
                    ) {
                        list.forEachIndexed{ index, menuOwnerModel ->
                            ItemMenuNavOwner(
                                iconNav = menuOwnerModel.menuIcon,
                                nameIcon = selectionMenuTitle[menuOwnerModel.titleName],
                                navMenu = menuOwnerModel.screensMenu,
                                navController = navController
                            )
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
                    .height(200.dp)
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
                        text = stringResource(R.string.GraphFinance),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(ROUND_CORNER.dp),
                    ){
                        Column(modifier = Modifier.padding(vertical = 16.dp)) {
//                            Log.d("debug_graph", "State = $INCOME_STATE")
                            if(INCOME_STATE == 1){
                                SampleLineGraph(listOf(LIST_INCOME_FLOAT, LIST_EXPENSES_FLOAT, LIST_PROFIT_FLOAT), selectGraph = selectedItem)
                            }
                            else if(INCOME_STATE == 3){
                                Box(
                                    modifier = Modifier.fillMaxWidth().height(200.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(R.string.DataEmpty),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            else{
                                Box(
                                    modifier = Modifier.fillMaxWidth().height(200.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                                    .horizontalScroll(rememberScrollState())
                            ) {
                                selectionFinanceGraphTitle.forEachIndexed{ index, mode ->
                                    Surface(color = if(selectedItem == index) MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f) else Color.Transparent,
                                        shape = RoundedCornerShape(ROUND_CORNER.dp),
                                        modifier = Modifier
                                            .wrapContentSize()
                                    ) {
                                        Surface(
                                            color = Color.Transparent
                                            ,modifier = Modifier
                                                .fillMaxSize()
                                                .selectable(
                                                    selected = (selectedItem == index),
                                                    onClick = {
                                                        selectedItem = index
//                                                        Log.d("debug_graph", "index = $selectedItem")
                                                              },
                                                    role = Role.RadioButton
                                                )
                                        ) {
                                            Text(
                                                text = mode,
                                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                modifier = Modifier
                                                    .padding(
                                                        horizontal = 10.dp,
                                                        vertical = 4.dp
                                                    ),
                                                color = if(selectedItem == index) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSecondary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}