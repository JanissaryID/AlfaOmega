package com.example.alfaomega.wallscreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
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
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.myFonts

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

    val alphaFont = 0.7f
    val fontSize = MaterialTheme.typography.titleMedium.fontSize


    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)) {

        val (CardForm, ButtonLogin) = createRefs()

        Column(
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(CardForm) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
//                    bottom.linkTo(parent.bottom)
                }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(144.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Icon TImer",
                        modifier = Modifier.size(112.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.LoginTitle),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                )
            }
            Spacer(modifier = Modifier.height(54.dp))

            OutlinedTextField(
                value = text_username,
                onValueChange ={
                    text_username = it
                },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alphaFont),
                    disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = alphaFont),
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alphaFont)
                ),
                shape = RoundedCornerShape(ROUND_CORNER.dp),
                modifier = Modifier.fillMaxWidth().height(72.dp),
                label = {
                    Text(
                        text = "Username",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alphaFont),
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = text_password,
                onValueChange ={
                    text_password = it
                },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alphaFont),
                    disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = alphaFont),
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alphaFont)

                ),
                shape = RoundedCornerShape(ROUND_CORNER.dp),
                modifier = Modifier.fillMaxWidth().height(72.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val visIcon = if (passwordVisible)
                        painterResource(id = R.drawable.ic_twotone_visibility_24)
                    else painterResource(id = R.drawable.ic_twotone_visibility_off_24)

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(painter  = visIcon, description, tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alphaFont))
                    }
                },
                label = {
                    Text(
                        text = "Password",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alphaFont),
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                    )
                }
            )
            Spacer(modifier = Modifier.height(36.dp))
            ButtonViewV2(
                title = stringResource(R.string.LoginTitle),
                enable = BUTTON_LOGIN,
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
        else if(FAILED_LOGIN_EMPTY){
            Toast.makeText(context, "Wrong username or password" , Toast.LENGTH_SHORT).show()
            FAILED_LOGIN_EMPTY = false
        }
        else if(FAILED_LOGIN_ALREADY){
            Toast.makeText(context, "User Already Login" , Toast.LENGTH_SHORT).show()
            FAILED_LOGIN_ALREADY = false
        }

        Text(
            text = "made by love ♥",
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alphaFont),
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            modifier = Modifier.constrainAs(ButtonLogin){
                bottom.linkTo(parent.bottom, 4.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )


    }
}