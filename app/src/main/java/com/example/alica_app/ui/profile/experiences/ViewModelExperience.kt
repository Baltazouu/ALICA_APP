package com.example.alica_app.ui.profile.experiences

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
                experience.startDate = outputFormat.format(startDate?: Date())
                if(experience.endDate != null){
                    val endDate = inputFormat.parse(experience.endDate!!)
                    experience.endDate = outputFormat.format(endDate?: Date())
                }
            } catch (e: Exception) {
                Log.e("PROFILE", "Error parsing dates", e)
            }
        }
    }

    fun addExperience(company: String, jobTitle: String, startDate: String, endDate: String, current: Boolean) {
        viewModelScope.launch {
            try {

                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())


                val startCalendar: Calendar = Calendar.getInstance()
                val endCalendar : Calendar= Calendar.getInstance()
Calendar.JULY
                Log.i("DATE BEFORE", startDate)


                // Date 23042004
                // JJMM AAAA
                startCalendar.set(startDate.substring(4).toInt(), startDate.substring(2, 4).toInt()-1, startDate.substring(0, 2).toInt())


                Log.i("MONTH",startDate.substring(2, 4))
                Log.i("DAY",startDate.substring(0, 2))
                Log.i("YEAR",startDate.substring(4))


                Log.i("MONTH INT",startDate.substring(2, 4).toInt().toString())
                Log.i("DAY INT",startDate.substring(0, 2).toInt().toString())
                Log.i("YEAR INT",startDate.substring(4).toInt().toString())

                Log.i("Mois",startCalendar.get(Calendar.MONTH).toString())
                Log.i("Jour",startCalendar.get(Calendar.DAY_OF_MONTH).toString())
                Log.i("Année",startCalendar.get(Calendar.YEAR).toString())

                val newStartDate = inputFormat.format(startCalendar.time)

               // 21 03 2004

                Log.i("DATE AFTER", newStartDate)

               // startCalendar.timeInMillis
                var newEndDate = "";



                if(endDate.isNotEmpty() && endDate.length == 8) {
                    endCalendar.set(endDate.substring(4).toInt(), endDate.substring(2, 4).toInt()-1, endDate.substring(0, 2).toInt())
                    newEndDate = inputFormat.format(endCalendar.time)
                }
                val experience = Experience(
                    id = null,
                    alumniId = authentication.id ?: "",
                    companyName = company,
                    title = jobTitle,
                    startDate = newStartDate,
                    endDate = newEndDate,
                    current = current
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
