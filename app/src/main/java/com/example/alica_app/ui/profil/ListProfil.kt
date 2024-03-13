package com.example.alica_app.ui.profil

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Mettre ici la liste des profils
@Composable
fun ListProfil() {
    Column {
        ProfilObject()
    }
}

// Ici, il faudra mettre un profil
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

}

@Composable
fun ProfileImage() {
    Box(modifier = Modifier
        .clip(shape = CircleShape)
        .defaultMinSize(60.dp, 60.dp)
        .background(color = Color.Blue)) {
        Text(text = "G", fontSize = 35.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 18.dp, top = 5.dp)
        )
    }
}

@Composable
fun ProfilCaracteristique(){
    Column {
        Text(text = "Nom : Gattor")
        Text(text = "Prenom : Allie")
        Text(text = "Développeuse chez CGI")
    }
}