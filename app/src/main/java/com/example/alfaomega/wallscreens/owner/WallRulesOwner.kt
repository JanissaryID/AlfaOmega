package com.example.alfaomega.wallscreens.owner

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.LIST_RULE
import com.example.alfaomega.RULE_STATE
import com.example.alfaomega.STORE_LIST_RESPONSE
import com.example.alfaomega.STORE_STATE
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.admin.store.StoreLoadData
import com.example.alfaomega.view.owner.rule.RuleLoadData

@Composable
fun WallRulesOwner(paddingValues: PaddingValues, navController: NavController) {
    Surface(color = Color.Transparent, modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RuleLoadData(
                ruleState = RULE_STATE,
                rule = LIST_RULE,
                navController = navController,
            )
        }
    }
}