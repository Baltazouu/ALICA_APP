package com.example.alica_app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.alica_app.NavigationItem
import com.example.alica_app.data.models.Alumni
import com.example.alica_app.data.models.Link
import com.example.alica_app.data.models.Links
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.ui.offers.offerDetail.OfferDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@Composable
fun Profile(viewModelProfile: ViewModelProfile,navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var alumni by remember { mutableStateOf<Alumni?>(null) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val result = viewModelProfile.getProfile()
            if (result) {
                isLoading = false
                alumni = viewModelProfile.alumni
            }
        }
    }

    Column {
        if (isLoading) {
            Text(text = "Loading")
        } else {
            ShowProfile(alumni!!,disconnect = {
                viewModelProfile.disconnect()
                navController.navigate(NavigationItem.SignIn.route)
            })
        }
    }
}





@Composable
fun ShowProfile(alumni: Alumni,disconnect:()->Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(10f)), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mon profil", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        TextButton(onClick = disconnect, modifier = Modifier.padding(Dp(10f))) {
            Text(text = "Déconnexion")
        }

        var page by remember { mutableIntStateOf(1) }
        if (page==1) {
            LazyRow {
                item {
                    Button(onClick = { page=1 }, modifier = Modifier.padding(Dp(10f))) {
                        Text(text = "Mes informations")
                    }
                }
                item{
                    FilledTonalButton(onClick = { page=2 }, modifier = Modifier.padding(Dp(10f))) {
                        Text(text = "Mes offres")
                    }
                }
            }
            Info(alumni)
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
        OfferDetail()
        OfferDetail()
    }
}

@Composable
fun Info(alumni: Alumni){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(Dp(15f)))
        .background(color = Color.LightGray)
        .padding(Dp(10f))
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        ProfilObject(alumni.firstName,alumni.lastName,alumni.email)
    }
}

@Composable
fun ProfilObject(firstName: String, lastName: String, email: String){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        ProfileImage(firstName) // Passer en paramètre l'image du profil
        Spacer(modifier = Modifier.padding(10.dp))
        ProfilCaracteristique(firstName,lastName,email) // Passer en paramètre, ou le profil, ou les 3 caractéristique (nom / prenom / metierActuel)
    }
    ProfilExperiences() // Passer en paramètre, ou le profil, ou les caractéristique
    ProfilFormations() // Passer en paramètre, ou le profil, ou les caractéristique
}

@Composable
fun ProfileImage(firstName: String) {
    Box(modifier = Modifier
        .clip(shape = CircleShape)
        .defaultMinSize(60.dp, 60.dp)
        .background(color = Color.White)) {
        Text(text = firstName.first().toString(), fontSize = 35.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 18.dp, top = 5.dp)
        )
    }
}

@Composable
fun ProfilCaracteristique(firstName:String,lastName:String,email:String){
    Column {
        Text(text = "$firstName $lastName")
        Text(text = email)
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
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Row {
            Text(text = "2019-2021 : ")
            Text(text = "BUT Informatique")
        }
    }
}


@Preview
@Composable
fun PreviewProfile(){
    ShowProfile(alumni = randomAlumni)
}

val randomAlumni = Alumni(
    id = "123456",
    email = "john.doe@example.com",
    role = "Software Engineer",
    entryYear = 2015,
    firstName = "John",
    lastName = "Doe",
    linkedinURL = "https://www.linkedin.com/in/johndoe",
    githubURL = "https://github.com/johndoe",
    portfolioURL = "https://johndoe.com",
    imageId = "image123",
    links = Links(
        self = Link(href = "https://example.com/alumni/123456"),
        offers = Link(href = "https://example.com/alumni/123456/offers"),
        events = Link(href = "https://example.com/alumni/123456/events"),
        formations = Link(href = "https://example.com/alumni/123456/formations")
    )
)
