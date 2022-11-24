package com.example.alfaomega.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DetailTransaction(
    transcationTitle: String,
    transcationType: String,
    transactionProcess: String,
    transactionAdmin: String
) {
    ConstraintLayout(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {

        val (iconCard,bodyCard) = createRefs()

        Card(
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .constrainAs(bodyCard){
                    top.linkTo(iconCard.top, 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = transcationTitle,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = transcationType,
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
//                            modifier = Modifier.background(color = Color.Transparent),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = transactionProcess,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = com.example.alfaomega.R.drawable.ic_baseline_account_circle_24),
                        contentDescription = "Icon Admin",
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = transactionAdmin,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                    )
                }
            }
        }

        Surface(modifier = Modifier.size(64.dp).constrainAs(iconCard){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, shape = RoundedCornerShape(100)) {
            Icon(
                painter = painterResource(id = com.example.alfaomega.R.drawable.ic_baseline_account_circle_24),
                contentDescription = "Icon Admin",
                modifier = Modifier.size(48.dp).padding(4.dp)
            )
        }
    }
}