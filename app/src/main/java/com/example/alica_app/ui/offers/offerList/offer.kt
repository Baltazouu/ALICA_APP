package com.example.alica_app.ui.offers.offerList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alica_app.NavigationItem
import com.example.alica_app.R


@Composable
fun Offers(navController: NavController){
    
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(10) {
            OfferCard(navController)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}


@Composable
fun OfferCard(navController: NavController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                Text(text = "Atos")
            }
            Column(verticalArrangement = Arrangement.Center
            ) {
                /*AsyncImage(
                   // model = "https://cap.img.pmdstatic.net/fit/https.3A.2F.2Fi.2Epmdstatic.2Enet.2Fcap.2F2023.2F10.2F23.2F707cc423-69b3-4a7b-bd99-5950a4f94059.2Ejpeg/1200x630/quality/80/atos-degringole-en-bourse-le-cours-plombe-par-lappel-a-la-nationalistation-temporaire-1483024.jpg",
                    model = "https://www.achetezenauvergne.fr/img/openium-logo-1644241059214.jpg",
                    modifier = Modifier
                        .width(150.dp)
                        .height(90.dp),
                    contentDescription = "Translated description of what the image contains",
                )*/
                Image(
                    painter = painterResource(id = R.drawable.coding_friends),
                    contentDescription = "Atos",
                    modifier = Modifier
                        .width(130.dp)
                        .height(80.dp))
            }
                Text(text = "Julien Martin | 06/04/2023")
                Row {
                    Column {
                        Icon(Icons.Filled.LocationOn, contentDescription = "location")
                    }
                    Column {
                        Text(text = "Clermont-Ferrand (63)")
                    }
                }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Développeur(euse) ASP .NET", fontSize = 10.sp)
            Text(text = "Stage | Junior", fontSize = 10.sp)
            Button(
                onClick = { navController.navigate(NavigationItem.OfferDetail.route){
                    popUpTo(NavigationItem.Offers.route) { inclusive = false }
                } },
                modifier = Modifier
                    .height(26.dp)) {
                Text(fontSize = 8.sp,text = "En savoir plus >")
            }
        }
    }

    
}