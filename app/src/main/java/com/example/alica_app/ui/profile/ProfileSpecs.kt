package com.example.alica_app.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alica_app.R
import com.example.alica_app.data.models.Alumni



@Composable
fun Info(alumni: Alumni,onClick: () -> Unit = {}){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(Dp(15f)))
        //.background(color = Color.LightGray)
        .padding(Dp(10f))
        .fillMaxWidth(),
        horizontalAlignment = Alignment.Start) {
        ProfileObject(alumni,onClick = onClick)
    }
}

@Composable
fun ProfileObject(alumni: Alumni,onClick: () -> Unit = {}){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        ProfileImage(alumni.firstName) // Passer en paramètre l'image du profil
        Spacer(modifier = Modifier.padding(10.dp))
        ProfileSpecs(alumni.firstName,alumni.lastName,alumni.email) // Passer en paramètre, ou le profil, ou les 3 caractéristique (nom / prenom / metierActuel)
        ClickableCircleIcon(icon = Icons.Filled.ManageAccounts, onClick = onClick )
    }
    ProfileLinks(alumni.linkedinURL ?: "",alumni.githubURL ?: "",alumni.portfolioURL ?: "",alumni.entryYear ?: "")
}

@Composable
fun ProfileImage(firstName: String) {
    Box(modifier = Modifier
        .clip(shape = CircleShape)
        .defaultMinSize(60.dp, 60.dp)
        .background(color = Color.LightGray)) {
        Text(text = firstName.first().toString(), fontSize = 35.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 18.dp, top = 5.dp)
        )
    }
}

@Composable
fun ProfileSpecs(firstName:String, lastName:String, email:String){
    Column {
        Text(text = "$firstName $lastName")
        Text(text = email)
    }
}






@Composable
fun ProfileLinks(linkedin:String, github:String, portfolio:String,entryYear:String){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(Dp(10f))) {

        Text(text = String.format("Année d'entrée : %s",entryYear),fontSize = 18.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp))
    }

    Text(text = "Liens :",fontSize = 18.sp, fontWeight = FontWeight.Bold,

        )
    Row(modifier = Modifier.padding(5.dp)) {
        // Text(text = "2019-2021 : ")
        Image(modifier = Modifier.width(20.dp),painter = painterResource(id = R.drawable.github), contentDescription = "github" )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = github)
    }
    Row(modifier = Modifier.padding(5.dp)) {
        Image(modifier = Modifier.width(20.dp),painter = painterResource(id = R.drawable.linkedin), contentDescription = "linkedin" )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = linkedin)
    }
    Row(modifier = Modifier.padding(5.dp)) {
        Icon(Icons.Filled.Language, contentDescription = "portfolio")
        //Image(modifier = Modifier.width(20.dp),painter = painterResource(id = R.drawable.linkedin), contentDescription = "linkedin" )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = portfolio)
    }

}


@Composable
fun EditProfileLinks(alumni: Alumni, onClick: (String, String, String, String) -> Unit = { _, _, _, _ -> }){


    var githubURL by remember { mutableStateOf(alumni.githubURL ?: "") }
    var linkedinURL by remember { mutableStateOf(alumni.linkedinURL ?: "" ) }
    var portfolioURL by remember { mutableStateOf(alumni.portfolioURL ?: "" ) }
    var entryYear by remember { mutableStateOf(alumni.entryYear.toString()) }

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(value = githubURL, onValueChange = {githubURL = it},
            label = { Text("Github ") })

        OutlinedTextField(value = linkedinURL, onValueChange = { linkedinURL = it},
            label = { Text("Linkedin ") })

        OutlinedTextField(value = portfolioURL, onValueChange = { portfolioURL = it},
            label = { Text("Portfolio") })


        OutlinedTextField(value = entryYear, onValueChange = {entryYear = it },
            label = { Text("Année d'entrée") })

        Button(onClick = { onClick(githubURL, linkedinURL, portfolioURL,entryYear) }, modifier = Modifier.width(150.dp)) {
            Text("Valider")
        }


    }

}

@Composable
fun ClickableCircleIcon(
    icon: ImageVector,
    iconSize: Dp = 24.dp,
    circleSize: Dp = 48.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(15.dp)
            .size(circleSize)
            .clickable(onClick = onClick)
            .background(Color.Cyan, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black, // Change color as needed
            modifier = Modifier.size(iconSize)
        )
    }
}
