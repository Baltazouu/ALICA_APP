package com.example.alica_app.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alica_app.NavigationItem
import com.example.alica_app.R
import com.example.alica_app.data.models.Alumni
import com.example.alica_app.data.models.Link
import com.example.alica_app.data.models.Links
import com.example.alica_app.ui.offers.offerDetail.OfferDetail
import kotlinx.coroutines.launch
import kotlin.math.hypot

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
            ShowProfile(navController,viewModelProfile,alumni!!,disconnect = {
                viewModelProfile.disconnect()
                navController.navigate(NavigationItem.SignIn.route)
            })
        }
    }
}





@Composable
fun ShowProfile(navController: NavController,viewModelProfile: ViewModelProfile,alumni: Alumni,disconnect:()->Unit = {}) {

    val coroutineScope = rememberCoroutineScope()


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

        var editProfileLinks by remember { mutableStateOf(false) }

        var showErrorProfileLinks by remember { mutableStateOf(false) }

        var showSuccesProfileLinks by remember { mutableStateOf(false) }

        LazyRow {
            item {
                Button(
                    onClick = { page = 1 },
                    modifier = Modifier.padding(Dp(10f)),
                    colors = ButtonDefaults.buttonColors(contentColor = if (page == 1) Color.Cyan else Color.Black)
                ) {
                    Text(text = "Mes informations")
                }
            }
            item {
                Button(
                    onClick = { page = 2 },
                    modifier = Modifier.padding(Dp(10f)),
                    colors = ButtonDefaults.buttonColors(contentColor = if (page == 2) Color.Cyan else Color.Black)
                ) {
                    Text(text = "Mes offres")
                }
            }
            item {
                Button(
                    onClick = { page = 3 },
                    modifier = Modifier.padding(Dp(10f)),
                    colors = ButtonDefaults.buttonColors(contentColor = if (page == 3) Color.Cyan else Color.Black)
                ) {
                    Text(text = "Mes événements")
                }
            }
            item {
                Button(
                    onClick = { page = 4 },
                    modifier = Modifier.padding(Dp(10f)),
                    colors = ButtonDefaults.buttonColors(contentColor = if (page == 4) Color.Cyan else Color.Black)
                ) {
                    Text(text = "Mes expériences")
                }
            }
            item {
                Button(
                    onClick = { page = 5 },
                    modifier = Modifier.padding(Dp(10f)),
                    colors = ButtonDefaults.buttonColors(contentColor = if (page == 5) Color.Cyan else Color.Black)
                ) {
                    Text(text = "Mes formations")
                }
            }
        }


        if (page==1) {

            LazyColumn(Modifier.background(Color.LightGray)) {

                item{
                    Info(alumni, onClick = {
                        editProfileLinks = !editProfileLinks
                    })
                }




            if (editProfileLinks) {

                item {
                    EditProfileLinks(alumni = alumni, onClick = { updatedGithubURL, updatedLinkedinURL, updatedPortfolioURL ->
                        editProfileLinks = false

                        coroutineScope.launch {

                            // Mettez à jour les valeurs d'alumni avec les nouvelles valeurs
                            alumni.githubURL = updatedGithubURL
                            alumni.linkedinURL = updatedLinkedinURL
                            alumni.portfolioURL = updatedPortfolioURL

                            val result = viewModelProfile.updateProfile(alumni)
                            if (result) {
                                //navController.navigate(NavigationItem.Profile.route)
                                showSuccesProfileLinks = true
                                showErrorProfileLinks = false
                            } else {
                                showErrorProfileLinks = true
                                showSuccesProfileLinks = false
                            }
                        }
                    })
                }
            }

            }

            if (showErrorProfileLinks) {
                Text(text = "Erreur lors de la mise à jour du profil", color = Color.Red)
            }
            if(showSuccesProfileLinks){
                Text(text = "Profil mis à jour avec succès", color = Color.Green)

            }
        }
        else if (page==2){

            Offers()
        }
        else if (page==3){
            Text(text = "TODO Evenements")
        }
        else if (page==4){
            ProfileExperiences()
        }
        else if (page==5){
            ProfileFormations()
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
fun Info(alumni: Alumni,onClick: () -> Unit = {}){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(Dp(15f)))
        .background(color = Color.LightGray)
        .padding(Dp(10f))
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
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
    ProfileLinks(alumni.linkedinURL ?: "",alumni.githubURL ?: "",alumni.portfolioURL ?: "")
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
fun ProfileSpecs(firstName:String, lastName:String, email:String){
    Column {
        Text(text = "$firstName $lastName")
        Text(text = email)
    }
}

@Composable
fun ProfileExperiences(){
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
fun ProfileFormations(){
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

@Composable
fun ProfileLinks(linkedin:String, github:String, portfolio:String){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(Dp(10f))) {
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
}


@Composable
fun EditProfileLinks(alumni: Alumni,onClick: (String, String, String) -> Unit = { _, _, _ -> }){


    var githubURL by remember { mutableStateOf(alumni.githubURL ?: "") }
    var linkedinURL by remember { mutableStateOf(alumni.linkedinURL ?: "" ) }
    var portfolioURL by remember { mutableStateOf(alumni.portfolioURL ?: "" ) }

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(value = githubURL, onValueChange = {githubURL = it},
            label = {Text("Github ")})

        OutlinedTextField(value = linkedinURL, onValueChange = { linkedinURL = it},
                label = {Text("Linkedin ")})

        OutlinedTextField(value = portfolioURL, onValueChange = { portfolioURL = it},
            label = {Text("Portfolio")})

        Button(onClick = { onClick(githubURL, linkedinURL, portfolioURL) }, modifier = Modifier.width(150.dp)) {
            Text("Valider")
        }


    }

}


@Preview
@Composable
fun PreviewProfile(){
    EditProfileLinks(alumni = randomAlumni)
}
