package com.example.alfaomega.view.admin.expenses

import android.bluetooth.BluetoothDevice
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.DATE_PICK
import com.example.alfaomega.R
import com.example.alfaomega.api.expenses.ExpensesModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.admin.bluetooth.BluetoothLazyColumn
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpensesLoadData(
    expensesState: Int,
    expenses: List<ExpensesModel>,
    navController: NavController,
//    protoViewModel: ProtoViewModel
) {
    val context = LocalContext.current
    val current = LocalDateTime.now()

    val formatDay = DateTimeFormatter.ofPattern("MM-yyyy")
    val dayNow = current.format(formatDay)
    val date = if(!DATE_PICK.isNullOrEmpty()) DATE_PICK.substring(startIndex = 3) else dayNow

    when (expensesState) {
        0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        1 -> {
            if (!expenses.isNullOrEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ExpensesLazyColumn(
                        expenses = expenses,
//                        protoViewModel = protoViewModel,
                        navController = navController
                    )
                }
            }
        }
        2 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Can't load data",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
//            Log.d("debug", "Error")
            Toast.makeText(context, "Can't load data", Toast.LENGTH_SHORT).show()
        }
        3 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {

                    val (StoreImage, TextEmpty) = createRefs()

                    Icon(painter = painterResource(
                        id = R.drawable.ic_twotone_list_alt_24),
                        contentDescription = stringResource(R.string.ExpensesEmpty),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .wrapContentHeight()
                            .size(200.dp)
                            .constrainAs(StoreImage)
                            {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = "$date, " + stringResource(R.string.ExpensesEmpty),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
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