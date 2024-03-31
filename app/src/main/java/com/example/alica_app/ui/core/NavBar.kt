package com.example.alica_app.ui.core

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.alica_app.NavigationItem


//@Preview
@Composable
fun NavBar(navController: NavController, ) {
    val items = arrayOf(NavigationItem.Offers,
                        NavigationItem.Home,
                        NavigationItem.Events,
                        NavigationItem.Alumnis)
    var selectedItem by remember { mutableIntStateOf(1) }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.route) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                    }
            )
        }
    }
}