package com.example.alica_app.ui.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Mettre ici la liste des profils
@Composable
fun ListProfil() {
    // LazyColum
    Column ( modifier = Modifier
        .background(Color.LightGray)
        //.requiredSize(200.dp)
        .verticalScroll(rememberScrollState())) {
        ProfilObject("Jean")
        ProfilObject("Marc")
        ProfilObject("toto")
        ProfilObject("askip")
        ProfilObject("Tchoupi")
        ProfilObject("Marie")
        ProfilObject("benoit")
    }
}

// Ici, il faudra mettre un profil
@Composable
fun ProfilObject(name : String) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        ProfileImage(name)// Passer en paramètre l'image du profil
        Spacer(modifier = Modifier.padding(10.dp))
        ProfilCaracteristique("Dupond", name , "Developpeur Full-Stack") // Passer en paramètre les 3 caractéristiques (nom / prenom / metierActuel)
    }

}

@Composable
fun ProfileImage(name : String) {
    Box(modifier = Modifier
        .clip(shape = CircleShape)
        .defaultMinSize(60.dp, 60.dp)
        .background(color = Color.Blue)) {
        Text(text = name.substring(0,0).uppercase(), fontSize = 35.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 18.dp, top = 5.dp)
        )
    }
}

@Composable
fun ProfilCaracteristique(name : String, surname : String, profil : String){
    Column {
        Text(text = "Nom : $name")
        Text(text = "Prenom : $surname")
        Text(text = profil)
    }
}