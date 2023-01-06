package com.example.alfaomega.view.admin.report

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.api.problem.ProblemModel
import com.example.alfaomega.api.rules.RuleModel
import com.example.alfaomega.components.ComponentRule

@Composable
fun ReportLazyColumn(
    reportModel: List<ProblemModel>,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier.padding(top = 8.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = reportModel) { index, problem ->
            ComponentRule(
                indexNumber = problem.machineNumber!!,
                ruleText = problem.problem!!,
                idRule = problem.id!!,
                navController = navController
            )
        }
    }
}