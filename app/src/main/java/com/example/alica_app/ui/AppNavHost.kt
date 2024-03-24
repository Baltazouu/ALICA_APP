package com.example.alica_app.ui

import ViewModelSignUp
import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.alica_app.NavigationItem
import com.example.alica_app.ui.SignUp.SignUpScreen
import com.example.alica_app.ui.signIn.SignIn



// NOT WORKING, TO DO LATER
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination:String = NavigationItem.SignUp.route){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination)
    {
        composable(NavigationItem.SignUp.route) {
            SignUpScreen(viewModel = ViewModelSignUp(), navController)
        }
        composable(NavigationItem.Login.route) {
            SignIn()
        }
    }
}