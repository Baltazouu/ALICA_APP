package com.example.alica_app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.R
import com.example.alica_app.ui.utils.BackgroundImageWithTitle
import com.example.alica_app.ui.utils.BackgroundImageWithTitleAndSubTitle
import com.example.alica_app.ui.utils.PreviewNextEvent

@Preview
@Composable
fun Home(navController: NavController = rememberNavController()) {
    LazyColumn {
        item {
            BackgroundImageWithTitleAndSubTitle(
                topText = "le Réseau Alica",
                title = "Alica Info",
                subtitle = "te souhaite la bienvenue !",
                ressource = R.drawable.bandeau
            )
        }
        item {
            PreviewNextEvent(topText = "Prochain événement",title = "LASERGAME" , subtitle ="13/04/2024", textButton = "En Savoir Plus", ressource = R.drawable.lasergame, onClick = {})
        }
        item {
             OurValues()
        }
    }

}


@Composable
fun OurValues() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .background(Color.Transparent)) {
        Column(modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.coding_friends), contentDescription = "coding friends")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Notre Association", fontSize = 20.sp, color = colorResource(id = R.color.alica_blue), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Alica - INFO est association d'anciens étudiants",fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(fontSize = 10.sp,text = "“Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ornare non sed est cursus. Vel hac convallis ipsum, facilisi odio pellentesque bibendum viverra tempus.”\n" +
                    "\n" +
                    "“Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ornare non sed est cursus. Vel hac convallis ipsum, facilisi odio pellentesque bibendum viverra tempus.”")
        }
    }
}


@Preview
@Composable
fun OurValuesPreview(){
    OurValues()
}
