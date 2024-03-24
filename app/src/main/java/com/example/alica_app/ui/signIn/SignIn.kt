package com.example.alica_app.ui.signIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alica_app.R
import com.example.alica_app.ui.SignUp.InputComponent
import com.example.alica_app.ui.SignUp.PasswordTextField

@Composable
@Preview
fun SignIn() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        item {
            AsyncImage(
                model = "https://www.achetezenauvergne.fr/img/openium-logo-1644241059214.jpg",
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .fillParentMaxWidth())
        }

        item {
            BackgroundImageWithTitle("test","Connexion")
        }
        item{
            Text(text = "Connecetez-vous", fontSize = 20.sp)

        }
        item{
            InputComponent("Email",email, { email= it },{})
        }
        item{
            PasswordTextField(label = "Password", text = password, updateText = { password = it }) {
            }
        }
        item{
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Connexion")
            }
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

@Composable
fun BackgroundImageWithTitle(
    contentDescription: String,
    text: String

){

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.bandeau),
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .blur(
                    radiusX = 4.dp,
                    radiusY = 4.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(5.dp))
                )
        )
        Text(text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp

        )
    }
}

