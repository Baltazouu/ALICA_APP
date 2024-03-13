package com.example.alica_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.example.alica_app.ui.contact.Contact
import com.example.alica_app.ui.home.NavBar
import com.example.alica_app.ui.profil.Profil
import com.example.alica_app.ui.theme.ALICA_APPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ALICA_APPTheme {
                Column{
                    NavBar()
                    /*Contact()*/
                    Profil()
                }
            }
        }
    }
}