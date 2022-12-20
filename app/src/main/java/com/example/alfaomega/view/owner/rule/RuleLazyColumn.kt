package com.example.alfaomega.view.owner.rule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.api.menu.MenuModel
import com.example.alfaomega.api.rules.RuleModel
import com.example.alfaomega.components.ComponentMenu
import com.example.alfaomega.components.ComponentRule

@Composable
fun RuleLazyColumn(
    ruleModel: List<RuleModel>,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier.padding(top = 8.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = ruleModel) { index, rule ->
            ComponentRule(
                indexNumber = index + 1,
                ruleText = rule.rule!!,
                idRule = rule.id!!,
                navController = navController
            )
        }
    }
}