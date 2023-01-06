package com.example.alfaomega.wallscreens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.LIST_PROBLEM_MACHINE
import com.example.alfaomega.PROBLEM_MACHINE_STATE
import com.example.alfaomega.view.admin.report.ReportLoadData

@Composable
fun WallReportMachine(paddingValues: PaddingValues, navController: NavController) {
    Surface(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReportLoadData(
                reportState = PROBLEM_MACHINE_STATE,
                report = LIST_PROBLEM_MACHINE,
                navController = navController,
            )
        }
    }
}