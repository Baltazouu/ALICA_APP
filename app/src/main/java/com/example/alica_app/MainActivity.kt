package com.example.alica_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.ui.AppNavHost
import com.example.alica_app.ui.SignUp.SignUpScreen
import com.example.alica_app.ui.signIn.SignIn
import com.example.alica_app.ui.theme.ALICA_APPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ALICA_APPTheme {

                // TO DO Later : utiliser le composant AppNavHost
                // Not working ??? idk why
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavigationItem.SignUp.route) {
                    composable(NavigationItem.SignUp.route) {
                        SignUpScreen(navController = navController)
                    }
                    composable(NavigationItem.Login.route) {
                        SignIn()
                    }
                }
            }
        }
    }
}