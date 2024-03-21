package com.example.alica_app.ui.SignUp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alica_app.data.models.SignUpBody
import com.example.alica_app.data.services.SignUpService
import com.example.alica_app.data.services.createSignUpRetrofit
import retrofit2.create

class ViewModelSignUp: ViewModel() {

    private val service = createSignUpRetrofit().create<SignUpService>()
    suspend fun signUp(firstName: String, lastName: String, emailAddress: String, password: String) {
        val signUpBody = SignUpBody(firstName, lastName, emailAddress, password)

        val string = signUpBody.toString()

        Log.i("SignUp", string)

        Log.i("before the crash","before the crash")

        service.signUp(signUpBody)
    }
}