package com.example.alica_app.ui.signIn

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.models.SignInBody
import com.example.alica_app.data.services.AuthenticationService
import com.example.alica_app.data.services.createAuthenticationRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ViewModelSignIn : ViewModel() {

    private val service = createAuthenticationRetrofit().create(AuthenticationService::class.java)

    val signInResponse = MutableLiveData<ResponseAuthentication?>(null)

    val failSignIn = MutableLiveData<Boolean>(false)

    fun signIn(email:String,password:String) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val signInBody = SignInBody(email, password)

                    val response = service.signIn(signInBody)

                    Log.i("RESPONSE","WORKED")

                    signInResponse.postValue(response)
                    failSignIn.postValue(false)               }
                catch (ex: HttpException) {
                    failSignIn.postValue(true)

                }
            }
        }
    }

}
