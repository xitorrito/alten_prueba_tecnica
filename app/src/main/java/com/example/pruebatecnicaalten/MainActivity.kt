package com.example.pruebatecnicaalten

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pruebatecnicaalten.domain.model.User
import com.example.pruebatecnicaalten.presentation.NavItem
import com.example.pruebatecnicaalten.presentation.ui.theme.PruebaTecnicaAltenTheme
import com.example.pruebatecnicaalten.presentation.ui.userdetail.UserDetailComposable
import com.example.pruebatecnicaalten.presentation.ui.userslist.UsersListComposable
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            PruebaTecnicaAltenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = NavItem.UserList.route) {
                        composable(NavItem.UserList.route) {
                            UsersListComposable(navController)
                        }
                        composable(
                            route = "${NavItem.UserDetail.route}/{user}",
                            arguments = listOf(navArgument("user") {
                                type = NavType.StringType
                            })
                        ) {
                            if (it.arguments?.containsKey("user") == true){
                                UserDetailComposable(
                                    navController = navController,
                                    user = Gson().fromJson(
                                        it.arguments?.getString("user"),
                                        User::class.java
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}