package com.example.alica_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP,
    EVENTS,
    EVENTDETAIL,
    OFFERS,
    PROFILE

}
sealed class NavigationItem(val route: String,val icon: ImageVector) {

    object SignUp : NavigationItem(Screen.SIGNUP.name,Icons.Filled.AppRegistration)
    object SignIn : NavigationItem(Screen.LOGIN.name,Icons.Filled.Login)
    object Home : NavigationItem(Screen.HOME.name,Icons.Filled.Home)
    object Events : NavigationItem(Screen.EVENTS.name,Icons.Filled.Event)
    object EventDetail : NavigationItem(Screen.EVENTDETAIL.name, Icons.Filled.AdsClick)
    object Offers : NavigationItem(Screen.OFFERS.name,Icons.Filled.WorkHistory)
    object Profile : NavigationItem(Screen.PROFILE.name,Icons.Filled.AccountCircle)
}