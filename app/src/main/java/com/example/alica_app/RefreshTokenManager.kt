package com.example.alica_app

import com.example.alica_app.data.models.RefreshToken
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.services.AuthenticationService
import com.example.alica_app.data.services.createAuthenticationRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RefreshTokenManager {

   private val authenticationService = createAuthenticationRetrofit().create(AuthenticationService::class.java)

    private var responseAuthentication: ResponseAuthentication? = null

    suspend fun refresh(token: String): Boolean {

        return withContext(Dispatchers.IO) {

            try {
                responseAuthentication =  authenticationService.refreshToken(RefreshToken(refreshToken = token))
                true

            }
            catch(ex : HttpException){
                false
            }
        }


    }

    fun getRefreshResponseAuthentication(): ResponseAuthentication {
        return responseAuthentication!!
    }
}