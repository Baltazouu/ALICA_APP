package com.example.alica_app.ui.profile.experiences

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alica_app.data.models.Experience
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.services.ExperiencesService
import com.example.alica_app.data.services.createExperienceRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ViewModelExperience(private val authentication: ResponseAuthentication) : ViewModel() {


    private val service = createExperienceRetrofit().create(ExperiencesService::class.java)

    private var experiences: List<Experience> = emptyList()


    suspend fun findExperiences(): Boolean {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->

                service.findAlumniExperiences(String.format("Bearer %s", authentication.token))
                    .enqueue(object : retrofit2.Callback<List<Experience>> {
                        override fun onResponse(call: retrofit2.Call<List<Experience>>, response: Response<List<Experience>>) {

                            if (response.isSuccessful) {
                                experiences = response.body() ?: emptyList()
                                continuation.resume(true)
                                Log.i("PROFILE", "Profile response: ${response.body()}")
                            } else {
                                Log.i("PROFILE  FAILED","Profile response: ${response.errorBody()}")
                                Log.i("PROFILE FAILED",response.toString())
                                continuation.resume(false)
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<List<Experience>>, t: Throwable) {
                            Log.e("PROFILE FAILURE",t.message,t)
                            continuation.resume(false)
                        }
                    })
            }
        }
    }

    suspend fun addExperience(): Boolean {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->

                service.addExperience(String.format("Bearer %s", authentication.token))
                    .enqueue(object : retrofit2.Callback<Experience> {
                        override fun onResponse(call: retrofit2.Call<Experience>, response: Response<Experience>) {

                            if (response.isSuccessful) {
                                continuation.resume(true)
                                Log.i("PROFILE", "Profile response: ${response.body()}")
                            } else {
                                Log.i("PROFILE  FAILED","Profile response: ${response.errorBody()}")
                                Log.i("PROFILE FAILED",response.toString())
                                continuation.resume(false)
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<Experience>, t: Throwable) {
                            Log.e("PROFILE FAILURE",t.message,t)
                            continuation.resume(false)
                        }
                    })
            }
        }
    }

    fun experiences(): List<Experience> {
        return experiences
    }

}