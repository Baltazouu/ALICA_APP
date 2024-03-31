package com.example.alica_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Details
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

enum class Item{
    OFFER_DETAIL
}
sealed class NavigationItem(val route: String,val icon: ImageVector) {

    object SignUp : NavigationItem(Screen.SIGNUP.name,Icons.Filled.AppRegistration)
    object SignIn : NavigationItem(Screen.LOGIN.name,Icons.Filled.Login)
    object Home : NavigationItem(Screen.HOME.name,Icons.Filled.Home)
    object Events : NavigationItem(Screen.EVENTS.name,Icons.Filled.Event)
    object Offers : NavigationItem(Screen.OFFERS.name,Icons.Filled.WorkHistory)
    object Profile : NavigationItem(Screen.PROFILE.name,Icons.Filled.AccountCircle)
    object OfferDetail : NavigationItem(Item.OFFER_DETAIL.name,Icons.Filled.Details)
    object Alumnis : NavigationItem(Screen.ALUMNIS.name,Icons.Outlined.VerifiedUser)
    object AddExperience : NavigationItem(Screen.ADD_EXPERIENCE.name,Icons.Outlined.WorkHistory)
}