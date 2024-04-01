package com.example.alica_app.ui.offers.offerDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alica_app.R
import com.example.alica_app.ui.utils.BackgroundImageWithTitle
import com.example.alica_app.ui.utils.BackgroundImageWithTitleAndSubTitle

@Preview
@Composable
fun OfferDetail() {
    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        item {
            BackgroundImageWithTitleAndSubTitle(
                topText = "",
                title = "Stage :",
                subtitle = "Développeur Android F/H",
                ressource = R.drawable.bandeau
            )
        }

        item{
            Text(text = "Offre proposée par  : Leo Tuaillon")
            Text(text = "Date de publication : 28/03/2024")
            Text(text = "Lieu de travail : Clermont-Ferrand")
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
        item {Modifier.padding(20.dp)
            Column {
                boxSpec(subtitle = "Desciptif du poste :",
                    content = "Nous recherchons un développeur Android pour un stage de 6 mois. Vous serez en charge de la création d'une application mobile pour notre entreprise.")
                boxSpec(subtitle = "Profil recherché :",
                    content = "Vous êtes étudiant en informatique et vous avez des compétences en développement Android. Vous êtes autonome et vous avez un bon relationnel.")
                boxSpec(subtitle = "Postuler :",
                    content = "cgi@recrutement.com | 04 73 00 00 00 | https://www.cgi.fr/")
            }

        }
    }
}


@Composable
fun boxSpec(subtitle:String = "TEXT", content:String = "TEXT"){
    Box(modifier = Modifier.padding(10.dp)) {
        Column {
            Text(text = subtitle, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
            Text(
                text = content,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.Cyan)
                    .padding(5.dp)
                    .fillMaxWidth(1f)
            )
        }
    }
}



@Composable
fun offerSpec(text:String = "TEXT"){
        Text(text = text, modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
            .padding(5.dp)
        )

}