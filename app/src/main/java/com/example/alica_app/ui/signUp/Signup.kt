package com.example.alica_app.ui.signUp

import ViewModelSignUp
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.NavigationItem
import com.example.alica_app.ui.core.NavBar
import com.example.alica_app.ui.utils.BackgroundImageWithTitle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen(
    viewModel: ViewModelSignUp = ViewModelSignUp(),
    navController: NavController
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var firstNameTouched by remember { mutableStateOf(false) }
    var lastNameTouched by remember { mutableStateOf(false) }
    var emailAddressTouched by remember { mutableStateOf(false) }
    var passwordTouched by remember { mutableStateOf(false) }

    var showSuccessMessage by remember { mutableStateOf(false) }
    var showErrorMessage by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val namesRegex = ".{3,}".toRegex()
    val emailRegex = android.util.Patterns.EMAIL_ADDRESS.toRegex()
    val passwordRegex = ".{6,}".toRegex()

   LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                BackgroundImageWithTitle("test", "S'inscrire")
            }
            item {
                InputComponent(
                    label = "Prénom",
                    text = firstName,
                    updateText = { firstName = it },
                    onFieldTouched = { firstNameTouched = true }
                )
                if (firstNameTouched) {
                    ErrorMessageComponent(
                        label = firstName,
                        errorMessage = "Firstname should contain at least 3 characters",
                        regex = namesRegex
                    )
                }
            }
            item {
                InputComponent(
                    label = "Nom",
                    text = lastName,
                    updateText = { lastName = it },
                    onFieldTouched = { lastNameTouched = true }
                )
                if (lastNameTouched) {
                    ErrorMessageComponent(
                        label = lastName,
                        errorMessage = "Lastname should contain at least 3 characters",
                        regex = namesRegex
                    )
                }
            }
            item {
                InputComponent(
                    label = "Email",
                    text = emailAddress,
                    updateText = { emailAddress = it },
                    onFieldTouched = { emailAddressTouched = true }
                )
                if (emailAddressTouched) {
                    ErrorMessageComponent(
                        label = emailAddress,
                        errorMessage = "Invalid email address",
                        regex = emailRegex
                    )
                }
            }
            item {
                PasswordTextField(
                    label = "Mot de passe",
                    text = password,
                    updateText = { password = it },
                    onFieldTouched = { passwordTouched = true }
                )
                if (passwordTouched) {
                    ErrorMessageComponent(
                        label = password,
                        errorMessage = "Password should contain at least 6 characters",
                        regex = passwordRegex
                    )
                }
            }
            item {
                SignupButtonComponent(signup = {
                    firstNameTouched = true
                    lastNameTouched = true
                    emailAddressTouched = true
                    passwordTouched = true

                    if (emailRegex.matches(emailAddress) &&
                        namesRegex.matches(firstName) &&
                        namesRegex.matches(lastName) &&
                        passwordRegex.matches(password)
                    ) {
                        Log.i("MESSAGE", "All fields are valid")

                        coroutineScope.launch {

                            val channel = Channel<Boolean>()

                            launch {
                                val signUpResult = viewModel.signUp(
                                    firstName,
                                    lastName,
                                    emailAddress,
                                    password
                                )
                                channel.send(signUpResult)
                            }

                            val childResult = channel.receive()

                            if (childResult == true) {
                                navController.navigate(NavigationItem.Login.route)
                                Log.i("PARENT", "success")
                                showSuccessMessage = true
                                showErrorMessage = false
                            } else {
                                Log.i("PARENT", "failure")
                                showErrorMessage = true
                                showSuccessMessage = false
                            }
                        }
                    }
                })
            }
            item {
                if (showSuccessMessage) {
                    SignUpSuccessResult()
                } else if (showErrorMessage) {
                    SignUpFailureResult()
                }
            }

    }
}

@Preview
@Composable
fun SignUpSuccessResult(){
        Text(text = "Sign up successful", color = Color.Green)
}

@Composable
fun SignUpFailureResult(){
    Text(text = "Sign up failed", color = Color.Red)
}


@Composable
fun ErrorMessageComponent(label: String, errorMessage: String, regex: Regex){

    if(!regex.matches(label)){
            Text(text = errorMessage, color = Color.Red)
        }
}


@Composable
fun InputComponent(label:String,
                   text:String,
                   updateText:(String) -> Unit,
                   onFieldTouched: () -> Unit)
{

        OutlinedTextField(modifier = Modifier
            .width(280.dp)
            .onFocusChanged {
                if (it.isFocused) {
                    onFieldTouched()
                }
            },
            shape = RoundedCornerShape(percent = 20),
            value = text,
            onValueChange = updateText,
            label = { Text(text = label) },
            )

}





@Composable
fun PasswordTextField(label: String,
                      text: String,
                      updateText: (String) -> Unit,
                      onFieldTouched: () -> Unit) {

    var showPassword by remember { mutableStateOf(value = false) }

    OutlinedTextField(
        modifier = Modifier
            .width(280.dp)
            .onFocusChanged { if (it.isFocused) onFieldTouched() },
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
fun SignupButtonComponent(signup:() -> Unit){

    OutlinedButton(onClick = signup, colors = ButtonDefaults.buttonColors(Color.Cyan)) {
        Text(text = "Signup", color = Color.Black)
    }

}