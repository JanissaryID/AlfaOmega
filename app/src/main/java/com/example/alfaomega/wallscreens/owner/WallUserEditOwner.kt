package com.example.alfaomega.wallscreens.owner

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.components.ButtonView
import com.example.alfaomega.components.button_view.ButtonViewV2
import com.example.alfaomega.navigations.Screens

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallUserEditOwner(
    paddingValues: PaddingValues,
    userViewModel: UserViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    var button_clicked by remember { mutableStateOf(false) }
    var text_name by remember {
        if(EDIT_MODE) mutableStateOf(TextFieldValue(USER_NAME_EDIT))
        else mutableStateOf(TextFieldValue(""))
    }

    var text_password by remember {
        if(EDIT_MODE) mutableStateOf(TextFieldValue(USER_PASSWORD_EDIT))
        else mutableStateOf(TextFieldValue(""))
    }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (CardMenu, Button) = createRefs()

            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
//                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .constrainAs(CardMenu) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                shape = RoundedCornerShape(ROUND_CORNER.dp)
            ) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Text(
                            text = stringResource(R.string.UsernameTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_name,
                            onValueChange ={
                                text_name = it
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.surface,
                                textColor = MaterialTheme.colorScheme.primary,
                                disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            shape = RoundedCornerShape(ROUND_CORNER.dp),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.PasswordTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_password,
                            onValueChange ={
                                text_password = it
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.surface,
                                textColor = MaterialTheme.colorScheme.primary,
                                disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            shape = RoundedCornerShape(ROUND_CORNER.dp),
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                val visIcon = if (passwordVisible)
                                    painterResource(id = R.drawable.ic_twotone_visibility_24)
                                else painterResource(id = R.drawable.ic_twotone_visibility_off_24)

                                // Please provide localized description for accessibility services
                                val description = if (passwordVisible) "Hide password" else "Show password"

                                IconButton(onClick = {passwordVisible = !passwordVisible}){
                                    Icon(painter  = visIcon, description, tint = MaterialTheme.colorScheme.primary)
                                }
                            }
                        )
                    }
                }
            }

            if(!text_name.text.isNullOrEmpty() && !text_password.text.isNullOrEmpty() && !button_clicked) {
                BUTTON_MENU_EDIT = true
            }
            else{
                BUTTON_MENU_EDIT = false
            }

            ButtonViewV2(
                title = if(USER_TYPE ==  2) if(EDIT_MODE) stringResource(R.string.SaveChanges) else stringResource(
                                    R.string.CreateownerTitle) else if(EDIT_MODE) stringResource(R.string.SaveChanges) else stringResource(R.string.CreateUserTitle),
                enable = BUTTON_MENU_EDIT,
                modifier = Modifier.constrainAs(Button){
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                button_clicked = true
                STATUS_USER_EXIST = false
                if(USER_TYPE == 2){
                    if(EDIT_MODE){
                        userViewModel.updateUser(
                            idUser = ID_USER_EDIT,
                            nameUser = text_name.text,
                            passwordUser = text_password.text,
                            ScreenDestination = Screens.OwnerListDeveloper.route,
                            navController = navController
                        )
                    }
                    else{
                        userViewModel.GetSimiliarUser(
                            username = text_name.text,
                            passwordUser = text_password.text,
                            idOwner = "#",
                            typeUser = 1,
                            ScreenDestination = Screens.OwnerListDeveloper.route,
                            navController = navController
                        )
                    }
                }
                else{
                    if(EDIT_MODE){
                        userViewModel.updateUser(
                            idUser = ID_USER_EDIT,
                            nameUser = text_name.text,
                            passwordUser = text_password.text,
                            ScreenDestination = Screens.UserOwner.route,
                            navController = navController
                        )
                    }
                    else{
                        userViewModel.GetSimiliarUser(
                            username = text_name.text,
                            passwordUser = text_password.text,
                            idOwner = OWNER_ID,
                            typeUser = 3,
                            ScreenDestination = Screens.UserOwner.route,
                            navController = navController
                        )
                    }
                }
            }

//            LaunchedEffect(true){
//
//            }
            if(STATUS_USER_EXIST){
                button_clicked = false
                Toast.makeText(context, "Username Already exist" , Toast.LENGTH_SHORT).show()
                STATUS_USER_EXIST = false
            }
        }
    }
}