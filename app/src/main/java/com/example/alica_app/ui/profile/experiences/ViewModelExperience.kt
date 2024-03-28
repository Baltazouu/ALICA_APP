package com.example.alica_app.ui.profile.experiences

import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alica_app.data.models.Experience
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.services.ExperiencesService
import com.example.alica_app.data.services.createExperienceRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.HttpException
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ViewModelExperience(private val authentication: ResponseAuthentication) : ViewModel() {

    private val service = createExperienceRetrofit().create(ExperiencesService::class.java)

    var experiences = MutableLiveData<List<Experience>>(emptyList())

    var successDeleteExperience = MutableLiveData(true)

    suspend fun findExperiences(): Boolean {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                service.findAlumniExperiences(String.format("Bearer %s", authentication.token))
                    .enqueue(object : retrofit2.Callback<List<Experience>> {
                        override fun onResponse(
                            call: retrofit2.Call<List<Experience>>,
                            response: Response<List<Experience>>
                        ) {
                            if (response.isSuccessful) {
                                experiences.value = response.body() ?: emptyList()
                                formatExperiences(experiences.value ?: emptyList())
                                continuation.resume(true)
                                Log.i("PROFILE", "Profile response: ${response.body()}")
                            } else {
                                Log.i("PROFILE  FAILED", "Profile response: ${response.errorBody()}")
                                Log.i("PROFILE FAILED", response.toString())
                                continuation.resume(false)
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<List<Experience>>, t: Throwable) {
                            Log.e("PROFILE FAILURE", t.message, t)
                            continuation.resume(false)
                        }
                    })
            }
        }
    }

    private fun formatExperiences(experiences: List<Experience>) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        experiences.forEach { experience ->
            try {
                val startDate = inputFormat.parse(experience.startDate)
                val endDate = inputFormat.parse(experience.endDate)
                experience.startDate = outputFormat.format(startDate?: Date())
                experience.endDate = outputFormat.format(endDate?: Date())
            } catch (e: Exception) {
                Log.e("PROFILE", "Error parsing dates", e)
            }
        }
    }

    fun addExperience(name: String, title: String, startDate: String, endDate: String, current: Boolean) {
        viewModelScope.launch {
            try {

                val inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy")
                val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00.000'Z'")


                val newStartDate = LocalDate.parse(startDate, inputFormatter).format(outputFormatter)
                val newEndDate = LocalDate.parse(endDate, inputFormatter).format(outputFormatter)

                val experience = Experience(
                    id = null,
                    alumniId = authentication.id ?: "",
                    companyName = name,
                    title = title,
                    startDate = newStartDate,
                    endDate = newEndDate,
                    isCurrent = current
                )

                Log.i("CURRENT FROM VIEWMODEL", current.toString())

                withContext(Dispatchers.IO) {

                    val responseExperience = service.addExperience(String.format("Bearer %s", authentication.token), experience)
                    if (responseExperience.id != null) {
                        experiences.value?.let { list ->
                            val updatedList = list.toMutableList()
                            updatedList.add(responseExperience)
                            experiences.postValue(updatedList)
                        }
                    }
                }
            } catch (ex: HttpException) {
                Log.e("Add Experience", "Error adding experience", ex)
            }
        }
    }


    fun deleteExperience(id:String){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.deleteExperience(
                        String.format("Bearer %s", authentication.token),
                        id
                    )
                }
                if (response.code() == 204) {
                    experiences.value?.let { list ->
                        val updatedList = list.filter { it.id != id }
                        experiences.postValue(updatedList)
                    }
                    successDeleteExperience.postValue(true)
                } else {
                    successDeleteExperience.postValue(false)
                }
            } catch (ex: HttpException) {
                Log.e("Delete Experience", "Error deleting experience", ex)
                successDeleteExperience.postValue(false)
            }
        }
    }



}
