package com.example.alica_app.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileFormations(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(Dp(10f))) {
        Text(text = "Formations :",fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Row {
            Text(text = "2019-2021 : ")
            Text(text = "BUT Informatique")
        }
    }
}