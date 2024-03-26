package com.example.alica_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.data.models.Alumni
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.models.Role
import com.example.alica_app.ui.SignUp.SignUpScreen
import com.example.alica_app.ui.alumnis.Alumnis
import com.example.alica_app.ui.core.NavBar
import com.example.alica_app.ui.core.TopBar
import com.example.alica_app.ui.event.events.Events
import com.example.alica_app.ui.home.Home
import com.example.alica_app.ui.offers.offerList.Offers
import com.example.alica_app.ui.profile.Profile
import com.example.alica_app.ui.profile.ViewModelProfile
import com.example.alica_app.ui.signIn.SignIn
import com.example.alica_app.ui.signIn.ViewModelSignIn
import com.example.alica_app.ui.theme.ALICA_APPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ALICA_APPTheme() {

                val authentication =  remember { mutableStateOf(ResponseAuthentication("", "", "", "", "", emptyList())) }

                val navController = rememberNavController()

                var currentPage by remember { mutableStateOf(NavigationItem.SignIn.route) }

                Scaffold(
                    topBar = { TopBar(navController,currentPage)},
                    bottomBar = {NavBar(navController = navController)}
                ) {padding ->
                    NavHost(modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = NavigationItem.Home.route) {
                        composable(NavigationItem.SignUp.route) {
                            currentPage = NavigationItem.SignUp.route
                            SignUpScreen(navController = navController)
                        }
                        composable(NavigationItem.SignIn.route) {
                            currentPage = NavigationItem.SignIn.route
                            SignIn(navController, viewModelSignIn = ViewModelSignIn(),authentication = authentication)
                        }
                        composable(NavigationItem.Home.route) {
                            currentPage = NavigationItem.Home.route
                            Home(navController)
                        }
                        composable(NavigationItem.Offers.route) {
                            currentPage = NavigationItem.Offers.route
                            Offers(navController)
                        }
                        composable(NavigationItem.Events.route) {
                            currentPage = NavigationItem.Events.route
                            Events(navController)
                        }
                        composable(NavigationItem.Profile.route){
                           currentPage = NavigationItem.Profile.route

                            if(authentication.value.token.isEmpty()){
                                navController.navigate(NavigationItem.SignIn.route)
                            }
                            Profile(viewModelProfile = ViewModelProfile(authentication.value),navController = navController)
                        }
                        composable(NavigationItem.Alumnis.route){
                            currentPage = NavigationItem.Alumnis.route
                            Alumnis(navController = navController)
                        }
                    }
                }
            }
        }
    }
}