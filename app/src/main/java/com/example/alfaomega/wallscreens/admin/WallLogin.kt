package com.example.alfaomega.wallscreens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.components.ButtonView
import com.example.alfaomega.proto.ProtoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallLogin(
    paddingValues: PaddingValues,
    navController: NavController,
    userViewModel: UserViewModel,
    protoViewModel: ProtoViewModel
) {

    var text_username by remember { mutableStateOf(TextFieldValue("")) }
    var text_password by remember { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)) {

        val (CardForm, ButtonLogin) = createRefs()

        Card(
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .constrainAs(CardForm) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(ButtonLogin.top)
                },
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Username",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = text_username,
                    onValueChange ={
                        text_username = it
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        textColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Password",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
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
                        containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        textColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    shape = RoundedCornerShape(12.dp),
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
                            Icon(painter  = visIcon, description, tint = MaterialTheme.colorScheme.surfaceVariant)
                        }
                    }
                )
            }
        }

        if(!text_username.text.isNullOrEmpty() && !text_password.text.isNullOrEmpty() && !BUTTON_LOGIN_CLICKED){
            BUTTON_LOGIN = true
        }
        else{
            BUTTON_LOGIN = false
        }

        if(FAILED_LOGIN){
            Toast.makeText(context, "Failed To Login" , Toast.LENGTH_SHORT).show()
            FAILED_LOGIN = false
        }

        ButtonView(
            title = "Login",
            enable = BUTTON_LOGIN,
            modifier = Modifier.constrainAs(ButtonLogin){
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {

            BUTTON_LOGIN_CLICKED = true

            userViewModel.GetUser(
                username = text_username.text,
                password = text_password.text,
                protoViewModel = protoViewModel,
                navController = navController
            )
        }
    }
}