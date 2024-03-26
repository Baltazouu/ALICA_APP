package com.example.alica_app.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.NavigationItem
import com.example.alica_app.R
import com.example.alica_app.data.models.Alumni
import com.example.alica_app.data.models.Link
import com.example.alica_app.data.models.Links
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.ui.offers.offerDetail.OfferDetail
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
            .padding(Dp(10f)), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Mon profil", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        TextButton(onClick = disconnect, modifier = Modifier.padding(Dp(10f))) {
            Text(text = "Déconnexion")
        }

        var page by remember { mutableIntStateOf(1) }

        var editProfileLinks by remember { mutableStateOf(false) }

        var showErrorProfileLinks by remember { mutableStateOf(false) }

        var showSuccessProfileLinks by remember { mutableStateOf(false) }

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


        when (page) {


            1 -> {
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                ) {

                    item{
                        Column(modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                        ){
                            Info(alumni, onClick = {
                                editProfileLinks = !editProfileLinks
                            })
                            if (editProfileLinks) {
                                EditProfileLinks(alumni = alumni, onClick = { updatedGithubURL, updatedLinkedinURL, updatedPortfolioURL,entryYear ->
                                    editProfileLinks = false

                                    coroutineScope.launch {

                                        alumni.githubURL = updatedGithubURL
                                        alumni.linkedinURL = updatedLinkedinURL
                                        alumni.portfolioURL = updatedPortfolioURL
                                        alumni.entryYear = entryYear

                                        val result = viewModelProfile.updateProfile(alumni)
                                        if (result) {
                                            //navController.navigate(NavigationItem.Profile.route)
                                            showSuccessProfileLinks = true
                                            showErrorProfileLinks = false
                                        } else {
                                            showErrorProfileLinks = true
                                            showSuccessProfileLinks = false
                                        }
                                    }
                                })
                            }

                            if (showErrorProfileLinks) {
                                Text(text = "Erreur lors de la mise à jour du profil", color = Color.Red)
                            }
                            if(showSuccessProfileLinks){
                                Text(text = "Profil mis à jour avec succès", color = Color.Green)

                            }

                        }

                    }
                }
            }
            2 -> {

                Offers()
            }
            3 -> {
                Text(text = "TODO Evenements")
            }
            4 -> {
                ProfileExperiences()
            }
            5 -> {
                ProfileFormations()
            }
        }
    }
}





val randomAlumni = Alumni(
    id = "123456",
    email = "john.doe@example.com",
    role = "Software Engineer",
    entryYear = "2015/2016",
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





val randomResponse = ResponseAuthentication(
    "",
    "",
    "",
    "",
    "",
    emptyList()
)

@Preview
@Composable
fun PreviewProfile() {
    ShowProfile(navController = rememberNavController(),viewModelProfile = ViewModelProfile(
        randomResponse),alumni = randomAlumni)
}

