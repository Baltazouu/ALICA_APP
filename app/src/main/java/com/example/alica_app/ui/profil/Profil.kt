package com.example.alica_app.ui.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alica_app.ui.offer.OfferDetail

@Composable
fun Profil() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(10f)), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mon profil")
        var page by remember { mutableIntStateOf(1) }
        if (page==1) {
            Row {
                Button(onClick = { page=1 }, modifier = Modifier.padding(Dp(10f))) {
                    Text(text = "Mes informations")
                }
                FilledTonalButton(onClick = { page=2 }, modifier = Modifier.padding(Dp(10f))) {
                    Text(text = "Mes offres")
                }
            }
            Info()
        }
        else if (page==2){
            Row {
                FilledTonalButton(onClick = { page=1 }, modifier = Modifier.padding(Dp(10f))) {
                    Text(text = "Mes informations")
                }
                Button(onClick = { page=2 }, modifier = Modifier.padding(Dp(10f))) {
                    Text(text = "Mes offres")
                }
            }
            Offers()
        }
    }
}

@Composable
fun Offers(){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(Dp(15f)))
        .background(color = Color.LightGray)
        .padding(Dp(10f))
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Mes offres :",
            fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        OfferDetail() // Modifier avec des vraies offres
        OfferDetail()
    }
}

@Composable
fun Info(){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(Dp(15f)))
        .background(color = Color.LightGray)
        .padding(Dp(10f))
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        ProfilObject()
    }
}

@Composable
fun ProfilObject() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        ProfileImage() // Passer en paramètre l'image du profil
        Spacer(modifier = Modifier.padding(10.dp))
        ProfilCaracteristique() // Passer en paramètre, ou le profil, ou les 3 caractéristique (nom / prenom / metierActuel)
    }
    ProfilExperiences() // Passer en paramètre, ou le profil, ou les caractéristique
    ProfilFormations() // Passer en paramètre, ou le profil, ou les caractéristique
}

@Composable
fun ProfileImage() {
    Box(modifier = Modifier
        .clip(shape = CircleShape)
        .defaultMinSize(60.dp, 60.dp)
        .background(color = Color.White)) {
        Text(text = "G", fontSize = 35.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 18.dp, top = 5.dp)
        )
    }
}

@Composable
fun ProfilCaracteristique(){
    Column {
        Text(text = "Allie Gattor")
        Text(text = "Développeuse chez CGI")
    }
}

@Composable
fun ProfilExperiences(){
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(Dp(10f))){
        Text(text = "Experiences :",fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline))
        Row {
            Text(text = "2021-2022 : ")
            Text(text = "Développeuse chez CGI")
        }
    }
}

@Composable
fun ProfilFormations(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(Dp(10f))) {
        Text(text = "Formations :",fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline))
        Row {
            Text(text = "2019-2021 : ")
            Text(text = "BUT Informatique")
        }
    }
}