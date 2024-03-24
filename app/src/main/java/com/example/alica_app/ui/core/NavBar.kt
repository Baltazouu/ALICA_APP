package com.example.alica_app.ui.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.alica_app.NavigationItem


@Preview
@Composable
fun NavBar() {
    val items = arrayOf(NavigationItem.Login,
                        NavigationItem.SignUp,
                        NavigationItem.Home,
                        NavigationItem.Offers,
                        NavigationItem.Events)
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.route) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}