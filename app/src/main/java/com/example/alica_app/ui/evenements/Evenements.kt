package com.example.alica_app.ui.evenements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alica_app.R


@Composable
fun Evenements() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(10f)), horizontalAlignment = Alignment.CenterHorizontally
    ){
        OneEvent()
    }
}

@Composable
fun OneEvent(){
    Column {
        Text(
            text = stringResource(R.string.event),
            fontSize = 35.sp,
            textAlign = TextAlign.Center
        )
        Row {
            AsyncImage(
                model = "https://www.mairie-bailly.fr/wp-content/uploads/2023/05/5d4d2f0c0ff89451695087.jpg",
                contentDescription = "Blabla",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .width(100.dp),
            )
            Column {
                Field(R.string.test_name)
                Field(R.string.test_city)
                Field(R.string.test_date)
                Field(R.string.test_limite)
                Field(R.string.test_inscrit)
            }
        }
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.details))
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.inscription))
            }
        }
    }
}

@Composable
fun Field(resources : Int){
    Text(
        text = stringResource(resources),
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}