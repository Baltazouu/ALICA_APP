package com.example.alica_app.ui.event.events

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alica_app.data.models.Alumni
import com.example.alica_app.data.models.Event
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.services.AlumniService
import com.example.alica_app.data.services.EventService
import com.example.alica_app.data.services.createAlumniRetrofit
import com.example.alica_app.data.services.createEventRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class EventViewModel(val responseAuthentication: ResponseAuthentication) : ViewModel() {

    private val service = createEventRetrofit().create(EventService::class.java)

    suspend fun  getEvent() : Boolean {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                service.getEvent(responseAuthentication.id, responseAuthentication.token)
                    .enqueue(object : retrofit2.Callback<Event> {
                        override fun onResponse(call: Call<Event>, response: Response<Event>) {
                            if (response.isSuccessful) {
                                continuation.resume(true)
                                Log.i("SUCCESS", "Event response: ${response.body()}")
                            } else {
                                continuation.resume(false)
                            }
                        }

                        override fun onFailure(call: Call<Event>, t: Throwable) {
                            Log.e("Error",t.message,t)
                            continuation.resume(false)
                        }
                    })
            }
        }
    }

    suspend fun  subscriber(event : Event) : Boolean {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                service.subscriber(responseAuthentication.id, event.nbRegistrations+1 ,responseAuthentication.token)
                    .enqueue(object : retrofit2.Callback<Event> {
                        override fun onResponse(call: Call<Event>, response: Response<Event>) {
                            if (response.isSuccessful) {
                                continuation.resume(true)
                                Log.i("SUCCESS", "Event response: ${response.body()}")
                            } else {
                                continuation.resume(false)
                            }
                        }

                        override fun onFailure(call: Call<Event>, t: Throwable) {
                            Log.e("Error",t.message,t)
                            continuation.resume(false)
                        }
                    })
            }
        }
    }

    suspend fun  unsubscriber(event : Event) : Boolean {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                service.unsubscriber(responseAuthentication.id, event.nbRegistrations-1 ,responseAuthentication.token)
                    .enqueue(object : retrofit2.Callback<Event> {
                        override fun onResponse(call: Call<Event>, response: Response<Event>) {
                            if (response.isSuccessful) {
                                continuation.resume(true)
                                Log.i("SUCCESS", "Event response: ${response.body()}")
                            } else {
                                continuation.resume(false)
                            }
                        }

                        override fun onFailure(call: Call<Event>, t: Throwable) {
                            Log.e("Error",t.message,t)
                            continuation.resume(false)
                        }
                    })
            }
        }
    }


}