package com.example.alfaomega.components.topbar

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.excel.ExcelViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar5(
    tittleScreen: String,
    navController: NavController,
    screenBack: String,
    icon: Int,
    description: String,
    transactionViewModel: TransactionViewModel = TransactionViewModel(),
    containerColor: Color = Color.Transparent,
    colorFont: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    excelViewModel: ExcelViewModel = ExcelViewModel()
) {

    val activity = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

//    val mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = DatePickerDialog(
        activity,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            DATE_PICK = "${(mDayOfMonth).toString().padStart(2, '0')}-${(mMonth+1).toString().padStart(2, '0')}-${(mYear).toString().padStart(4, '0')}"
//            Log.d("debug_transaction", "Date ${DATE_PICK}")
            transactionViewModel.getTransactionNowDate()
        }, mYear, mMonth, mDay
    )

    SmallTopAppBar(
        title = {
            Text(
                text = tittleScreen,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = colorFont
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            Surface(color = Color.Transparent, shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "Back",
                    modifier = Modifier.clickable {
                        navController.navigate(route = screenBack) {
                            popUpTo(screenBack) {
                                inclusive = true
                            }
                        }
                    },
                    tint = colorFont
                )
            }
        },
        actions = {
            Surface(shape = RoundedCornerShape(20), modifier = Modifier.wrapContentSize()) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = description,
                    modifier = Modifier.clickable {
                        if(USER_TYPE == 1){
                            mDatePickerDialog.show()
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            Surface(shape = RoundedCornerShape(20), modifier = Modifier.wrapContentSize().padding(end = 8.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_twotone_download_24),
                    contentDescription = description,
                    modifier = Modifier.clickable {
                        if(USER_TYPE == 1){
                            Toast.makeText(activity, "Please Wait", Toast.LENGTH_SHORT).show()
                            excelViewModel.createExcelSheet(date = DATE_PICK)
                            if(excelViewModel.stateExcel == 1){
                                Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    tint = colorFont
                )
            }
        }
    )
}