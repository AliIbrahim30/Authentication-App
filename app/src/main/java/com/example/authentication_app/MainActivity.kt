package com.example.authentication_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authentication_app.data.remote.datastore.DataStoreManager
import com.example.authentication_app.home.HomeScreen
import com.example.authentication_app.login.LoginScreen
import com.example.authentication_app.ui.theme.AuthenticationAppTheme
import com.example.authentication_app.util.Routes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AuthenticationAppTheme {
                val navController = rememberNavController()
                val token by dataStore.getToken.collectAsState(initial = null)
                NavHost(
                    navController= navController,
                    startDestination= if (token.isNullOrEmpty()) {
                        Routes.LOGIN
                    } else {
                        Routes.HOME
                    }
                ){
                    composable (Routes.LOGIN){
                        LoginScreen(navController=navController)
                    }
                    composable (Routes.HOME){
                        HomeScreen(navController=navController)
                    }
                }
                }
            }
        }
    }



