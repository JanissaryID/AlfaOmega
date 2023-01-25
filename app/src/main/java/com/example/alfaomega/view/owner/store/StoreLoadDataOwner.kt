package com.example.alfaomega.view.owner.store

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.api.store.StoreModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.admin.store.StoreLazyColumn

@Composable
fun StoreLoadDataOwner(
    storeState: Int,
    store: List<StoreModel>,
    navController: NavController,
    protoViewModel: ProtoViewModel,
    storeViewModel: StoreViewModel = StoreViewModel()
) {
    val context = LocalContext.current

//    Log.i("Check State", "This Is $menuState")

    when (storeState) {
        0 -> {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        1 -> {
//            Log.d("debug", "Success")
            if (!store.isNullOrEmpty()){
                Box(
                    modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    StoreLazyCRow(
                        storeModel = store,
                        navController = navController,
                        protoViewModel = protoViewModel
                    )
                }
            }
        }
        2 -> {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {

                    val (StoreImage, TextEmpty) = createRefs()

                    Surface(shape = RoundedCornerShape(22.dp), color = Color.Transparent, modifier = Modifier.size(40.dp).constrainAs(StoreImage)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }) {
                        Icon(painter = painterResource(
                            id = R.drawable.ic_twotone_refresh_24),
                            contentDescription = "Store Empty",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxSize()
                                .size(60.dp)
                                .clickable {
                                    storeViewModel.FetchStore()
                                }
                        )
                    }

                    Text(
                        text = "Cant Load Data",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .wrapContentHeight()
                            .constrainAs(TextEmpty)
                            {
                                top.linkTo(StoreImage.bottom, 8.dp)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    )
                }
            }
//            Log.d("debug", "Error")
            Toast.makeText(context, "Can't load data", Toast.LENGTH_SHORT).show()
        }
        3 -> {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {

                    val (StoreImage, TextEmpty) = createRefs()

                    Surface(shape = RoundedCornerShape(22.dp), color = Color.Transparent, modifier = Modifier.size(40.dp).constrainAs(StoreImage)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }) {
                        Icon(painter = painterResource(
                            id = R.drawable.ic_twotone_refresh_24),
                            contentDescription = "Store Empty",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxSize()
                                .size(60.dp)
                                .clickable {
                                    storeViewModel.FetchStore()
                                }
                        )
                    }

                    Text(
                        text = "Store Empty",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .wrapContentHeight()
                            .constrainAs(TextEmpty)
                            {
                                top.linkTo(StoreImage.bottom, 8.dp)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    )
                }
            }
        }
        4 -> {
            Toast.makeText(context, stringResource(R.string.TryAgainTitle), Toast.LENGTH_SHORT).show()
        }
    }
}