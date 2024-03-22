package com.example.alica_app.ui.event

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter

@Composable
fun EventList() {
    val title = "Evenements"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
            .size(200.dp)
    ) {
        Row {
            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        EventObject("Clermont-Ferrand")
        EventObject("Mets")
        EventObject("Lille")
        EventObject("Vichy")
    }
}

@Composable
fun EventObject(city: String) {
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
            ClickableImage("https://www.achetezenauvergne.fr/img/openium-logo-1644241059214.jpg")
            {  }
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
fun ClickableImage(
    imageUrl: String,
    onClick: () -> Unit
) {
    val painter = rememberImagePainter(
        data = imageUrl,
    )

    Box(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.height(35.dp).width(25.dp),
            contentScale = ContentScale.FillBounds
        )
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