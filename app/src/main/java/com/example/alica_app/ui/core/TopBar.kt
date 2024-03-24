package com.example.alica_app.ui.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alica_app.NavigationItem


@Composable
fun TopBar(navController: NavController,title: String){
    Row(horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(50.dp).padding(5.dp).fillMaxWidth()) {
            Text(text = title)
            IconButton(modifier = Modifier.fillMaxHeight(),onClick = { navController.navigate(NavigationItem.SignIn.route) }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "back")
            }
        }

    }
