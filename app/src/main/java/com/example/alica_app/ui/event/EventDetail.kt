package com.example.alica_app.ui.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Preview
@Composable
fun EventDetail(){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){

            AsyncImage(
                model = "https://www.mairie-bailly.fr/wp-content/uploads/2023/05/5d4d2f0c0ff89451695087.jpg",
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier.fillMaxWidth())

            Column(modifier = Modifier.background(Color.White).padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    Text("LunaPark",textAlign = TextAlign.Center, fontSize = 30.sp)
                }
                Row {
                    Text(text = "27/02/2023")

                }
        }
        // Text and date row

        }


        Row {
            Column {
                Text(text = "Organisé Par : ")
            }
            Column {
                Text(text = "Marvin Brout")
            }
            Column {
                AsyncImage(
                    model = "https://img.freepik.com/photos-gratuite/portrait-beau-jeune-homme-barbu-seul-expression-serieuse-mur-gris-copie-espace_231208-7778.jpg",
                    contentDescription = "Translated description of what the image contains",
                   modifier = Modifier
                       .height(30.dp)
                       .width(30.dp)
                )
            }
        }

        Row{
            Text(text = "Description de L'évènement")
        }
        Row {
            Text(modifier = Modifier
                .background(Color.Gray)
                .padding(5.dp), text = "Rejoignez-nous pour une soirée de laser game entre anciens et \n" +
                    "actuels étudiants ! \n" +
                    "Affrontez-vous dans un labyrinthe, renforcez votre réseau, \n" +
                    "et vivez des moments mémorables. \n" +
                    "Ne manquez pas cet événement !")
        }
        Row {
            Text(text = "Lieu:")
        }

        Row {
            Text(modifier = Modifier
                .background(Color.Gray)
                .padding(5.dp), text ="Clermont-Ferrand - 63 000\n" + "Stade Marcel Michelin")
        }

        Row {
            Text(text = "Participants :")
        }

        Row {
            Text(modifier = Modifier
                .background(Color.Gray)
                .padding(5.dp), text ="Limite de participants : 45 \nNombre d'inscrits : 27")
        }

        Row {
            TextButton(onClick = { /*TODO*/ },) {
                Text(text = "Je m'inscris !")
                
            }
        }
    }






}
