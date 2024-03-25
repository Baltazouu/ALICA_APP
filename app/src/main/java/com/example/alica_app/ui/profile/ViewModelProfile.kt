package com.example.alica_app.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alica_app.data.models.Alumni
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.services.AlumniService
import com.example.alica_app.data.services.createAlumniRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ViewModelProfile(val responseAuthentication: ResponseAuthentication): ViewModel() {

    private val service = createAlumniRetrofit().create(AlumniService::class.java)

    var alumni: Alumni? = null

    suspend fun getProfile(): Boolean {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                service.getAlumni(responseAuthentication.id, String.format("Bearer %s",responseAuthentication.token))
                    .enqueue(object : retrofit2.Callback<Alumni> {
                        override fun onResponse(call: retrofit2.Call<Alumni>, response: Response<Alumni>) {
                            if (response.isSuccessful) {
                                continuation.resume(true)
                                alumni = response.body()

                                Log.i("PROFILE", "Profile response: ${response.body()}")
                            } else {
                                Log.i("PROFILE  FAILED","Profile response: ${response.errorBody()}")
                                Log.i("PROFILE FAILED",response.toString())
                                continuation.resume(false)
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<Alumni>, t: Throwable) {
                            Log.e("PROFILE FAILURE",t.message,t)
                            continuation.resume(false)
                        }
                    })
            }
        }
    }

    fun response(): ResponseAuthentication {
        return responseAuthentication
    }

    fun alumni(): Alumni? {
        return alumni
    }
}
