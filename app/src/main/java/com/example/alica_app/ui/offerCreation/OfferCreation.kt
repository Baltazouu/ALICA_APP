package com.example.alica_app.ui.offerCreation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import com.example.alica_app.R
import com.example.alica_app.ui.home.NavBar


@Composable
fun OfferCreation() {
    Column{
        NavBar()
        Text(stringResource(R.string.title_offer_creation))
        OfferField(R.string.entitled_offer)
        OfferField(R.string.description_offer)
        OfferField(R.string.city_offer)
        OfferField(R.string.job_description_offer)
        OfferField(R.string.seek_job_offer)
        OfferField(R.string.experience_offer)
        OfferField(R.string.studies_level_offer)
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Publier")
        }
    }
}

@Composable
fun OfferField(resources : Int){
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {text = it},
        label = { stringResource(resources) }
    )
}