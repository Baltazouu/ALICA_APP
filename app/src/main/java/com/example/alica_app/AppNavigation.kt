package com.example.alica_app

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP
}
sealed class NavigationItem(val route: String) {

    object SignUp : NavigationItem(Screen.SIGNUP.name)
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
}