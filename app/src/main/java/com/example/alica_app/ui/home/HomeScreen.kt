package com.example.alica_app.ui.home

import androidx.compose.foundation.layout.Column

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavController = rememberNavController()) {
    Column() {
        Text(text ="Home")
        Text(text = "TO do")
    }

}