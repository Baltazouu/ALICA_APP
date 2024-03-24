package com.example.alica_app.ui.event.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.ui.core.NavBar


@Composable
fun Events(navController: NavController = rememberNavController()) {
        Column() {
            Text(text ="Events")
            Text(text = "TO do")
        }
}