package com.example.alica_app.ui.signIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.alica_app.NavigationItem
import com.example.alica_app.R
import com.example.alica_app.ui.signUp.InputComponent
import com.example.alica_app.ui.signUp.PasswordTextField
import com.example.alica_app.ui.utils.BackgroundImageWithTitle
import com.google.android.gms.wallet.button.ButtonConstants

@Composable
@Preview
fun SignIn(navController: NavController = rememberNavController()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {

        BackgroundImageWithTitle("test","Connexion")
        Text(text = "Connecetez-vous", fontSize = 20.sp)

        InputComponent("Email",email, { email= it },{})
        PasswordTextField(label = "Password", text = password, updateText = { password = it }) {
        }

        Button(onClick = { navController.navigate(NavigationItem.SignUp.route) },colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.alica_blue))){
            Text(text = "Connexion")
        }

        Text(text = "Pas encore Inscrit ?", fontSize = 18.sp)
        TextButton(onClick = { navController.navigate(NavigationItem.SignUp.route) }) {
            Text(text = "S'inscrire", color = colorResource(id = R.color.alica_blue))

        }
    }
}



@Preview
@Composable
fun offerSpec(text:String = "TEXT"){
    Text(text = text, modifier = Modifier
        .background(Color.Gray)
        .padding(5.dp))

}



