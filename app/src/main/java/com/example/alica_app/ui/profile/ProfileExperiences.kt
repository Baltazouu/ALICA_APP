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
fun ProfileExperiences(){
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(Dp(10f))){
        Text(text = "Experiences :",fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Row {
            Text(text = "2021-2022 : ")
            Text(text = "Développeuse chez CGI")
        }
    }
}