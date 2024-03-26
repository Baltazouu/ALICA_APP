package com.example.alica_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AppRegistration
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material.icons.outlined.WorkHistory
import androidx.compose.material.icons.rounded.WorkHistory
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP,
    EVENTS,
    OFFERS,
    PROFILE,
    ALUMNIS,
    ADD_EXPERIENCE,

}
sealed class NavigationItem(val route: String,val icon: ImageVector) {

    object SignUp : NavigationItem(Screen.SIGNUP.name,Icons.Outlined.AppRegistration)
    object SignIn : NavigationItem(Screen.LOGIN.name,Icons.AutoMirrored.Outlined.Login)
    object Home : NavigationItem(Screen.HOME.name,Icons.Outlined.Home)
    object Events : NavigationItem(Screen.EVENTS.name,Icons.Outlined.Event)
    object Offers : NavigationItem(Screen.OFFERS.name,Icons.Outlined.WorkHistory)
    object Profile : NavigationItem(Screen.PROFILE.name,Icons.Outlined.AccountCircle)
    object Alumnis : NavigationItem(Screen.ALUMNIS.name,Icons.Outlined.VerifiedUser)

    object AddExperience : NavigationItem(Screen.ADD_EXPERIENCE.name,Icons.Outlined.WorkHistory)
}