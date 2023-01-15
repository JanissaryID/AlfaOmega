package com.example.alfaomega.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.navigations.Screens

@Composable
fun ComponentRule(
    ruleText: String,
    indexNumber: Int,
    idRule: String,
    solvedMachine: Boolean = false,
    navController: NavController
) {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.clickable(
                enabled = !solvedMachine
            ) {
                if(USER_TYPE == 1) {
                    EDIT_MODE = true
                    RULE_TEXT_EDIT = ruleText
                    RULES_SCREEN_TYPE = true
                    ID_RULE_EDIT = idRule
                    navController.navigate(route = Screens.RulesEditOwner.route)
                }
            }) {
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
            ){
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = indexNumber.toString(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = ruleText,
                        fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    )
                }
            }
        }
    }
}