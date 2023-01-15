package com.example.alfaomega.view.admin.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.api.menu.MenuModel
import java.text.NumberFormat
import java.util.*
import com.example.alfaomega.components.ComponentMenu as ComponentMenu

@Composable
fun MenuLazyColumn(
    menuModel: List<MenuModel>,
    navController: NavController,
    selectedIndex: Int
) {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.setMaximumFractionDigits(0)

    val selectionMenuClass = listOf(stringResource(R.string.MenuGiant), stringResource(R.string.MenuTitan))

    LazyColumn(
        modifier = Modifier.padding(top = 8.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = menuModel) { index, menu ->
            ComponentMenu(
                menuTitle = menu.menuTitle.toString(),
                menuType = if(selectedIndex == 0) selectionMenuClass[0] else selectionMenuClass[1],
                menuPrice = menu.menuPrice.toString(),
                menuTime = menu.menuTime!!,
                isWasher = menu.isWasher!!,
                isDryer = menu.isDryer!!,
                isService = menu.isService!!,
                menuClass = menu.menuClass!!,
                idmenu = menu.id!!,
                priceTitle = numberFormat.format(menu.menuPrice!!.toInt()),
                navController = navController
            )
        }
    }
}