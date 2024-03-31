package com.example.alica_app

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.ui.SignUp.SignUpScreen
import com.example.alica_app.ui.alumnis.Alumnis
import com.example.alica_app.ui.core.NavBar
import com.example.alica_app.ui.core.TopBar
import com.example.alica_app.ui.event.events.Events
import com.example.alica_app.ui.home.Home
import com.example.alica_app.ui.offers.offerDetail.OfferDetail
import com.example.alica_app.ui.offers.offerList.Offers
import com.example.alica_app.ui.profile.Profile
import com.example.alica_app.ui.profile.ViewModelProfile
import com.example.alica_app.ui.profile.experiences.AddExperience
import com.example.alica_app.ui.profile.experiences.ViewModelExperience
import com.example.alica_app.ui.signIn.SignIn
import com.example.alica_app.ui.signIn.ViewModelSignIn
import com.example.alica_app.ui.theme.ALICA_APPTheme

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var authentication: MutableState<ResponseAuthentication>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ALICA_APPTheme {
                sharedPreferences = getSharedPreferences("authentication", Context.MODE_PRIVATE)

                authentication = remember {
                    mutableStateOf(
                        ResponseAuthentication(
                            sharedPreferences.getString("token", "") ?: "",
                            "",
                            "",
                            sharedPreferences.getString("alumniId", "") ?: "",
                            sharedPreferences.getString("refreshToken", "") ?:"",
                            emptyList()
                        )
                    )
                }


                LaunchedEffect(Unit) {

                    if(authentication.value.token != ""){


                        val refreshTokenManager = RefreshTokenManager()

                        Log.i("REFRESH TOKEN",authentication.value.refreshToken)
                        Log.i("Alumni Id",authentication.value.id ?: "")


                        val result = refreshTokenManager.refresh(authentication.value.refreshToken)

                        if(result){

                            val response = refreshTokenManager.getRefreshResponseAuthentication()

                            //authentication.value.id = response.id
                            authentication.value.token = response.token
                           // authentication.value.email = response.email
                            authentication.value.refreshToken = response.refreshToken

                            Log.i("REFRESH","WORKED")

                        }
                        else{
                            authentication.value = ResponseAuthentication(
                                "",
                                "",
                                "",
                                "",
                                "",
                                emptyList()
                            )
                            Log.i("REFRESH","FAILED")
                        }
                    }

                }


                Log.i("token", authentication.value.token)

                val navController = rememberNavController()

                var currentPage by remember { mutableStateOf(NavigationItem.SignIn.route) }

                Scaffold(
                    topBar = { TopBar(navController, currentPage) },
                    bottomBar = { NavBar(navController = navController) }
                ) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = NavigationItem.Home.route
                    ) {
                        composable(NavigationItem.SignUp.route) {
                            currentPage = NavigationItem.SignUp.route
                            SignUpScreen(navController = navController)
                        }
                        composable(NavigationItem.SignIn.route) {
                            currentPage = NavigationItem.SignIn.route
                            SignIn(navController, viewModelSignIn = ViewModelSignIn(), authentication = authentication)
                        }
                        composable(NavigationItem.Home.route) {
                            currentPage = NavigationItem.Home.route
                            Home(navController)
                        }
                        composable(NavigationItem.Offers.route) {
                            currentPage = NavigationItem.Offers.route
                            Offers(navController)
                        }
                        composable(NavigationItem.OfferDetail.route){
                            currentPage = NavigationItem.OfferDetail.route
                            OfferDetail()
                        }
                        composable(NavigationItem.Events.route) {
                            currentPage = NavigationItem.Events.route
                            Events(navController)
                        }
                        composable(NavigationItem.Profile.route) {
                            currentPage = NavigationItem.Profile.route

                            if (authentication.value.id == null || authentication.value.id  =="") {
                                navController.navigate(NavigationItem.SignIn.route)
                            }
                            Profile(
                                viewModelProfile = ViewModelProfile(authentication.value),
                                viewModelExperience = ViewModelExperience(
                                    authentication = authentication.value
                                ),
                                navController = navController
                            )
                        }
                        composable(NavigationItem.Alumnis.route) {
                            currentPage = NavigationItem.Alumnis.route
                            Alumnis(navController = navController)
                        }
                        composable(NavigationItem.AddExperience.route) {
                            currentPage = NavigationItem.AddExperience.route
                            AddExperience(navController, viewModelExperience = ViewModelExperience(authentication = authentication.value))
                        }
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        with(sharedPreferences.edit()) {
            putString("token", authentication.value.token)
            putString("email", authentication.value.email)
            putString("alumniId", authentication.value.id)
            putString("refreshToken", authentication.value.refreshToken)
            apply()
        }
    }
}
