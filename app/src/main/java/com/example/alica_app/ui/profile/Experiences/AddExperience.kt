package com.example.alica_app.ui.profile.Experiences


import DateInput
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.ui.utils.InputComponent


@Composable
fun AddExperience(navController: NavController) {

    val experienceName = remember { mutableStateOf("") }
    val company = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
        }
        Column(verticalArrangement = Arrangement.SpaceEvenly,
               horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Ajouter une experience", fontSize = 18.sp)

            }

            InputComponent(
                label = "Poste",
                text = experienceName.value,
                updateText = { experienceName.value = it }) {}

            InputComponent(
                label = "Entreprise",
                text = company.value,
                updateText = { company.value = it }) {}

            DateInput(onDateChanged = {}, label = "Date Début")
            DateInput(onDateChanged = {}, label = "Date Fin")

            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)) {
                Text(text = "Ajouter")
            }
        }
    }

}



@Preview
@Composable
fun AddExperiencePreview() {
    AddExperience(navController = rememberNavController())

}

