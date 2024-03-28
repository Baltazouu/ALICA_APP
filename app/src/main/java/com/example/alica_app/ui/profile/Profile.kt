package com.example.alica_app.ui.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.NavigationItem
import com.example.alica_app.data.models.Alumni
import com.example.alica_app.data.models.Link
import com.example.alica_app.data.models.Links
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.ui.profile.experiences.ProfileExperiences
import com.example.alica_app.ui.profile.experiences.ViewModelExperience
import kotlinx.coroutines.launch

@Composable
fun Profile(viewModelProfile: ViewModelProfile,
            viewModelExperience: ViewModelExperience,
            navController: NavController) {

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

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            ShowProfile(navController,viewModelProfile,
                viewModelExperience,alumni = alumni!!,disconnect = {
                viewModelProfile.disconnect()
                navController.navigate(NavigationItem.SignIn.route)
            })
        }
    }
}



@Composable
fun ShowProfile(navController: NavController,viewModelProfile: ViewModelProfile,
                viewModelExperience: ViewModelExperience,
                alumni: Alumni,disconnect:()->Unit = {}, ) {

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
                EditProfileLinks(viewModelProfile = viewModelProfile, alumni = alumni)
            }
            2 -> {

                Offers()
            }
            3 -> {
                Text(text = "TODO Evenements")
            }
            4 -> {
                ProfileExperiences(viewModelExperience = viewModelExperience,navController = navController)
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

@Composable
fun EditProfileLinks(viewModelProfile: ViewModelProfile, alumni: Alumni) {

    val coroutineScope = rememberCoroutineScope()
    var editProfileLinks by remember { mutableStateOf(false) }

    var showErrorProfileLinks by remember { mutableStateOf(false) }
    var showSuccessProfileLinks by remember { mutableStateOf(false) }

    LazyColumn(
        Modifier
            .fillMaxSize()
    ) {
        item {
            Info(alumni, onClick = {
                editProfileLinks = !editProfileLinks
            })
        }
        if (editProfileLinks) {
            item {
                EditProfileLinks(alumni = alumni, onClick = { updatedGithubURL, updatedLinkedinURL, updatedPortfolioURL, entryYear ->
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
        }

        if (showErrorProfileLinks) {
            item {
                Text(text = "Erreur lors de la mise à jour du profil", color = Color.Red)
            }
        }
        if (showSuccessProfileLinks) {
            item {
                Text(text = "Profil mis à jour avec succès", color = Color.Green)
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfile() {
    ShowProfile(navController = rememberNavController(),viewModelProfile = ViewModelProfile(randomResponse),alumni = randomAlumni, viewModelExperience = ViewModelExperience(
        randomResponse),disconnect = {})
}

