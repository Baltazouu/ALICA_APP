package com.example.alica_app.ui.alumnis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Alumnis(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(10) {
            AlumniCard()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Preview
@Composable
fun AlumniCard(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Absolute.Left) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(5.dp).fillMaxWidth()) {
            Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center ){
                Column {
                    Icon(Icons.Filled.AccountCircle, contentDescription = "Profil icon")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column {
                    Text(text = "Julien Martin")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column (horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Voir le profil")
                    }
                }
            }
        }
    }


}