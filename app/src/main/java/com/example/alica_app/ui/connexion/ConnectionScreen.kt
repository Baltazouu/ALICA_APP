package com.example.alica_app.ui.connexion
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alica_app.R

@Composable
@Preview
fun ConnectionScreen() {
    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        item {
            AsyncImage(
                model = "https://www.achetezenauvergne.fr/img/openium-logo-1644241059214.jpg",
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .fillParentMaxWidth())
        }

        item {
            BackgroundImageWithTitle("test","Connexion")
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
    }
}

@Preview
@Composable
fun offerSpec(text:String = "TEXT"){
    Text(text = text, modifier = Modifier
        .background(Color.Gray)
        .padding(5.dp))

}

@Composable
fun BackgroundImageWithTitle(
    contentDescription: String,
    text: String

){

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.bandeau),
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .blur(
                    radiusX = 4.dp,
                    radiusY = 4.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(5.dp))
                )
        )
        Text(text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp

        )
    }
}

