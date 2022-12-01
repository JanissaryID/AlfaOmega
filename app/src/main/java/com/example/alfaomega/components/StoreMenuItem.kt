package com.example.alfaomega.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.alfaomega.R

@Composable
fun StoreMenuItem(
    title: String,
    subTitle: String,
    iconMenu: Int
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier.clickable {  }
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            val (IconMenu, Tittle, Subtittle) = createRefs()

            Surface(
                border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(100),
                modifier = Modifier.constrainAs(IconMenu){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Icon(
                    painter = painterResource(id = iconMenu),
                    contentDescription = "Icon TImer",
                    modifier = Modifier
                        .size(52.dp)
                        .padding(13.dp)
                )
            }
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = Modifier.constrainAs(Tittle){
                    start.linkTo(IconMenu.end, 16.dp)
                    top.linkTo(IconMenu.top, 5.dp)
                },
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = subTitle,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                modifier = Modifier.constrainAs(Subtittle){
                    start.linkTo(IconMenu.end, 16.dp)
                    bottom.linkTo(IconMenu.bottom, 5.dp)
                }
            )
        }
    }
}