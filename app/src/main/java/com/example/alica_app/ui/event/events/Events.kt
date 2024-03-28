package com.example.alica_app.ui.event.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.alica_app.NavigationItem
import com.example.alica_app.ui.core.NavBar
import com.example.alica_app.ui.offers.offerList.OfferCard


@Composable
fun Events(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(10) {
            EventObject("Clermont-Ferrand", navController)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun EventObject(city: String, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Row {
            EventImage("https://www.achetezenauvergne.fr/img/openium-logo-1644241059214.jpg")
            Spacer(modifier = Modifier.padding(20.dp))
            EventCaracteristique("Team Bulding", "24/02/2024", 30, 18)
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row {
            Text("description")
            Spacer(modifier = Modifier.padding(10.dp))
            ClickableImage(navController)
        }

        Text(city)
        Row {
            buttonClick(name = "S'inscrire", color = Color.Blue) {}
            Spacer(modifier = Modifier.padding(20.dp))
            buttonClick(name = "Supprimer", color = Color.Red) {}
        }
    }
}

@Composable
fun ClickableImage(navController: NavController){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(50.dp).padding(5.dp).fillMaxWidth()
    )
    {
        IconButton(modifier = Modifier.fillMaxHeight(),onClick = { navController.navigate(
            NavigationItem.EventDetail.route) }) {
            Icon(Icons.Filled.AdsClick, contentDescription = "back")
        }
    }

}

@Composable
fun buttonClick(name: String, color : Color, onClick : () -> Unit){

    Button(onClick = { onClick }) {
        Text(text = name)
    }
}

@Composable
fun EventImage(url : String ) {
    AsyncImage(
        model = url,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier
            .height(80.dp)
            .width(80.dp))
}

@Composable
fun EventCaracteristique(title : String, date : String, nbMax : Int, nbInscrit : Int) {

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, fontSize = 20.sp)
        Text(text = "Date limite : $date")
        Text(text = "nombre limite : $nbMax")
        Text(text = "nombre d'inscrit : $nbInscrit")

    }
}