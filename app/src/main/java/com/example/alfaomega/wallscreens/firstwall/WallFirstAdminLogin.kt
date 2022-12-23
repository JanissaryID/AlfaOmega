package com.example.alfaomega.wallscreens.firstwall

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.`object`.owner.MenuOwner
import com.example.alfaomega.`object`.owner.MenuOwnerModel
import com.example.alfaomega.navigations.Screens

@Composable
fun WallFirstAdminLogin(
    paddingValues: PaddingValues
) {
    var list: ArrayList<MenuOwnerModel> = arrayListOf()
    list.addAll(MenuOwner.listData)

    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )) {

        ConstraintLayout(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {

            val (IconMenu, ExplainText) = createRefs()

            Text(
                text = "Please Login to start operate",
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .width(320.dp)
                    .constrainAs(ExplainText){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}