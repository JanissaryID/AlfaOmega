package com.example.alfaomega.view.owner.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.api.rules.RuleModel
import com.example.alfaomega.api.user.UserModel
import com.example.alfaomega.components.ComponentRule
import com.example.alfaomega.components.ComponentUser
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun UserLazyColumn(
    userModel: List<UserModel>,
    navController: NavController,
    protoViewModel: ProtoViewModel
) {
    LazyColumn(
        modifier = Modifier.padding(top = 8.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = userModel) { index, user ->
            ComponentUser(
                idUser = user.id!!,
                name = user.username!!,
                password = user.passwordUser!!,
                navController = navController,
                protoViewModel = protoViewModel
            )
        }
    }
}