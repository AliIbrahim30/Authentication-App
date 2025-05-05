package com.example.authentication_app.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.authentication_app.login.LoginViewModel
import com.example.authentication_app.util.Routes

@Composable
fun HomeScreen(
    navController: NavController,
   viewModel: LoginViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                viewModel.logOut{
                    navController.navigate(Routes.LOGIN){
                        popUpTo(navController.graph.findNode(Routes.HOME)?.id ?:return@navigate)
                    }
                }
            }
        ) {
            Text(text = "Logout" , fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }

}