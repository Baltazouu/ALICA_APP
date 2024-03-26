package com.example.alica_app.ui.offers.offerDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Preview
@Composable
fun OfferDetail() {

    Column {
        AsyncImage(
            model = "https://www.achetezenauvergne.fr/img/openium-logo-1644241059214.jpg",
            contentDescription = "Translated description of what the image contains",
            modifier = Modifier
                .height(70.dp)
                .width(70.dp))
        Text(text = "Stage : Développeur Android F/H", fontSize = 20.sp)
        Text(text = "Clermont-Ferrand")
        Row(modifier = Modifier.padding(30.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Column {
                offerSpec(text = "Stage")
            }
            Column {
                offerSpec(text = "Etudes : Bac+3")
            }
            Column {
                offerSpec("Experience : Junior")
            }
        }
    }

    /*LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        item {
            AsyncImage(
                model = "https://www.achetezenauvergne.fr/img/openium-logo-1644241059214.jpg",
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp))
        }

        item{
            Text(text = "Stage : Développeur Android F/H", fontSize = 20.sp)
        }
        item{
            Text(text = "Clermont-Ferrand")
        }
        item{
            Row(modifier = Modifier.padding(30.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                Column {
                    offerSpec(text = "Stage")
                }
                Column {
                    offerSpec(text = "Etudes : Bac+3")
                }
                Column {
                    offerSpec("Experience : Junior")
                }
            }
        }
    }*/

}


@Preview
@Composable
fun offerSpec(text:String = "TEXT"){
        Text(text = text, modifier = Modifier
            .background(Color.Gray)
            .padding(5.dp))

}