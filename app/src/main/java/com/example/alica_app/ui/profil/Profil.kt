package com.example.alica_app.ui.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.Dp
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
        Row {
            Button(onClick = { page=1 }, modifier = Modifier.padding(Dp(10f))) {
                Text(text = "Modifier")
            }
            Button(onClick = { page=2 }, modifier = Modifier.padding(Dp(10f))) {
                Text(text = "Mes offres")
            }
        }
        Column(modifier = Modifier.clip(shape = RoundedCornerShape(Dp(15f))).background(color = Color.LightGray)
                .padding(Dp(10f)).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            if (page==1){
                Text(text = "Modifier")
            }
            else if (page==2){
                Text(text = "Mes offres :")
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Ajouter")
                }
                OfferDetail()
            }
        }
    }
}