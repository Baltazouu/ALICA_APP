package com.example.alica_app.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alica_app.data.models.ResponseAuthentication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Profile(viewModelProfile: ViewModelProfile ) {

    val coroutineScope = rememberCoroutineScope()

   /* coroutineScope.launch {
        viewModelProfile.getProfile();
    }*/


    Column(modifier = Modifier.fillMaxSize()) {

        Text(text = "Connected : ")
        Text(text = viewModelProfile.responseAuthentication.email);
    }
}



@Preview
@Composable
fun PreviewProfile() {
   // Profile(ResponseAuthentication("token", "type", "email", "id", "refreshToken", emptyList()))
}