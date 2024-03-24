package com.example.alica_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.ui.core.NavBar
import com.example.alica_app.ui.core.TopBar
import com.example.alica_app.ui.signUp.SignUpScreen
import com.example.alica_app.ui.event.events.Events
import com.example.alica_app.ui.home.Home
import com.example.alica_app.ui.offer.offer.Offers
import com.example.alica_app.ui.offer.offer.Offers
import com.example.alica_app.ui.signIn.SignIn
import com.example.alica_app.ui.theme.ALICA_APPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ALICA_APPTheme() {

                // TO DO Later : décaler dans le composant AppNavHost
                // Not working ??? idk why
                val navController = rememberNavController()

                Scaffold(
                    topBar = { TopBar()},
                    bottomBar = {NavBar(navController = navController)}
                ) {padding ->
                    NavHost(modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = NavigationItem.Home.route) {
                        composable(NavigationItem.SignUp.route) {
                            SignUpScreen(navController = navController)
                        }
                        composable(NavigationItem.Login.route) {
                            SignIn(navController)
                        }
                        composable(NavigationItem.Home.route) {
                            Home(navController)
                        }
                        composable(NavigationItem.Offers.route) {
                            Offers(navController)
                        }
                        composable(NavigationItem.Events.route) {
                            Events(navController)
                        }
                    }
                }

            }
        }
    }
}