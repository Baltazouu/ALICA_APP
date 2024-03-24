package com.example.alica_app

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP,
    EVENTS,
    OFFERS,

}
sealed class NavigationItem(val route: String,val icon: ImageVector) {

    object SignUp : NavigationItem(Screen.SIGNUP.name,Icons.Filled.AppRegistration)
    object Login : NavigationItem(Screen.LOGIN.name,Icons.Filled.Login)
    object Home : NavigationItem(Screen.HOME.name,Icons.Filled.Home)
    object Events : NavigationItem(Screen.EVENTS.name,Icons.Filled.Event)

    object Offers : NavigationItem(Screen.OFFERS.name,Icons.Filled.WorkHistory)
}