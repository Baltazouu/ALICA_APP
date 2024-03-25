package com.example.alica_app.ui.signIn

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.models.SignInBody
import com.example.alica_app.data.services.AuthenticationService
import com.example.alica_app.data.services.createAuthenticationRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ViewModelSignIn : ViewModel() {

    private val service = createAuthenticationRetrofit().create(AuthenticationService::class.java)

    val signInResponse = MutableLiveData<ResponseAuthentication>()

    suspend fun signIn(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val signInBody = SignInBody(email, password)

            suspendCoroutine<Boolean> { continuation ->
                service.signIn(signInBody).enqueue(object : retrofit2.Callback<ResponseAuthentication> {
                    override fun onResponse(call: retrofit2.Call<ResponseAuthentication>, response: Response<ResponseAuthentication>) {

                        Log.i("CHILD", "Sign in response: ${response.body()}")
                        if (response.isSuccessful) {
                            Log.i("CHILD", "Sign in successful")
                            signInResponse.postValue(response.body())
                            continuation.resume(true)
                        } else {
                            continuation.resume(false)
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<ResponseAuthentication>, t: Throwable) {

                        Log.e("CHILD", "Sign in failure", t)
                        Log.e("CHILD",  t.message,t)
                        continuation.resume(false)
                    }
                })
            }
        }
    }
}
