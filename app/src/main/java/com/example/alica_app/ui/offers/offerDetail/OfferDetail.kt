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
    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {

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
            Box(modifier = Modifier.padding(10.dp)){
                Text(text = "Descriptif du poste :")
                Text(text = "CECI EST LA DESCRIPtion de test \n test\n\t test",
                    modifier = Modifier
                        .background(color = Color.Cyan)
                        .padding(5.dp)
                        .fillMaxWidth(1f)
                )
            }
        }
    }

}


@Preview
@Composable
fun offerSpec(text:String = "TEXT"){
        Text(text = text, modifier = Modifier
            .background(Color.Gray)
            .padding(5.dp))

}