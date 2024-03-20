package com.example.alica_app.ui.SignUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alica_app.ui.signIn.BackgroundImageWithTitle


@Composable
@Preview
fun SignUpScreen(
    viewModel: ViewModelSignUp = ViewModelSignUp()
) {

     var firstName by remember { mutableStateOf("") }
     var lastName by remember { mutableStateOf("") }
     var emailAddress by remember { mutableStateOf("") }
     var password by remember { mutableStateOf("") }

    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(15.dp)) {


        item {
            BackgroundImageWithTitle("test","SignUp")
        }
        item{
            InputComponent(label = "FirstName", text = firstName, updateText = {firstName = it})
        }
        item{
            InputComponent(label = "LastName", text = lastName, updateText = {emailAddress = it})
        }
        item{
            InputComponent(label = "Email", text = emailAddress, updateText = {emailAddress = it})
        }
        item{
            ShowHidePasswordTextField(label = "Password",text = password, updateText = { password = it});
        }
        item {
            SignupButtonComponent();
        }
    }

    ViewModelSignUp().signUp(firstName, lastName, emailAddress, password)
}


@Composable
fun InputComponent(label:String, text:String,updateText:(String) -> Unit) {

        OutlinedTextField(modifier = Modifier.width(280.dp),
            shape = RoundedCornerShape(percent = 20),
            value = text,
            onValueChange = updateText,
            label = { Text(text = label) })

}


@Composable
fun ShowHidePasswordTextField(label: String,text: String,updateText: (String) -> Unit) {

    var showPassword by remember { mutableStateOf(value = false) }

    OutlinedTextField(
        modifier = Modifier.width(280.dp),
        value = text,
        onValueChange =  updateText ,
        label = {
            Text(text = label)
        },
        placeholder = { Text(text = "Type password here") },
        shape = RoundedCornerShape(percent = 20),
        visualTransformation = if (showPassword) {

            VisualTransformation.None

        } else {

            PasswordVisualTransformation()

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPassword = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide_password"
                    )
                }
            }
        }
    )
}

@Composable
fun SignupButtonComponent(){

    OutlinedButton(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Color.Cyan)) {
        Text(text = "Signup", color = Color.Black)
    }

}