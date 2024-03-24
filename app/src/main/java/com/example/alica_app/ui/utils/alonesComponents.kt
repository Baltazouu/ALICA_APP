package com.example.alica_app.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alica_app.R


@Composable
fun BackgroundImageWithTitle(
    contentDescription: String,
    text: String

){

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
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

@Composable
fun BackgroundImageWithTitleAndSubTitle(topText:String,title: String,
                                        subtitle:String,ressource:Int){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = ressource),
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .blur(
                    radiusX = 2.dp,
                    radiusY = 2.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(5.dp))
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(text = topText,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Text(
                text = title,
                fontFamily = TextStyle.Default.fontFamily,
                color = Color.Black,//colorResource(R.color.alica_blue),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Text(
                text = subtitle,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}


@Composable
fun PreviewNextEvent(topText:String,
                     title: String,
                     subtitle:String,
                     ressource:Int,
                     textButton:String,
                     onClick:()->Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = ressource),
            contentDescription = title,
            modifier = Modifier
                .width(350.dp)
                .height(180.dp)
                .blur(
                    radiusX = 1.dp,
                    radiusY = 1.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(5.dp)),

                    )
                .border(
                    BorderStroke(2.dp, SolidColor(colorResource(id = R.color.alica_blue))),
                    shape = RoundedCornerShape(5.dp)
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(text = topText,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Text(
                text = title,
                fontFamily = TextStyle.Default.fontFamily,
                color = colorResource(R.color.alica_blue),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Text(
                text = subtitle,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Button(modifier = Modifier.width(120.dp).height(30.dp),onClick = { onClick() }) {
                Text(text = textButton, fontSize = 10.sp)
            }
        }
    }
}

/*@Preview
@Composable
fun BackgroundImageWithTitlePreview() {
    BackgroundImageWithTitleAndSubTitle(topText = "le Réseau Alica",title = "Alica Info" , subtitle ="te souhaite la bienvenue !", ressource = R.drawable.bandeau )
}*/

@Preview
@Composable
fun BackgroundImageWithTitleAndSubTitlePreview() {
    PreviewNextEvent(topText = "Prochain événement",title = "LASERGAME" , subtitle ="13/04/2024", textButton = "En Savoir Plus", ressource = R.drawable.lasergame, onClick = {})
}