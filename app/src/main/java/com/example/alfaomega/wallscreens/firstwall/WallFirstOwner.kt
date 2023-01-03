package com.example.alfaomega.wallscreens.firstwall

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.STORE_ADDRESS
import com.example.alfaomega.STORE_CITY
import com.example.alfaomega.STORE_NAME
import com.example.alfaomega.`object`.owner.MenuOwner
import com.example.alfaomega.`object`.owner.MenuOwnerModel
import com.example.alfaomega.components.ItemStoreMenu
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun WallFirstOwner(
    navController: NavController,
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

            Card(
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .size(144.dp)
                    .constrainAs(IconMenu){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                ,
                shape = RoundedCornerShape(100),
            ) {
                Surface(
                    shape = RoundedCornerShape(100),
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screens.Store.route)
                    },
                    color = Color.Transparent
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_twotone_storefront_24),
                            contentDescription = "Icon TImer",
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            }

            Text(
                text = stringResource(R.string.TextPickStoreFirst),
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .width(320.dp)
                    .constrainAs(ExplainText){
                    start.linkTo(parent.start)
                    top.linkTo(IconMenu.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}