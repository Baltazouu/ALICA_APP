import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alica_app.data.models.SignUpBody
import com.example.alica_app.data.services.AuthenticationService
import com.example.alica_app.data.services.createAuthenticationRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelSignUp : ViewModel() {


    private val service = createAuthenticationRetrofit().create(AuthenticationService::class.java)
    val signUpResult = MutableLiveData<Boolean>()

    suspend fun signUp(firstName: String, lastName: String, emailAddress: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val signUpBody = SignUpBody(firstName, lastName, emailAddress, password)
            val resultChannel = Channel<Boolean>()

            service.signUp(signUpBody).enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Log.i("CHILD", "Sign up successful")
                        resultChannel.trySend(true).isSuccess
                    } else {
                        Log.e("CHILD", "Sign up failed")
                        resultChannel.trySend(false).isSuccess
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("CHILD", "Sign up failure", t)
                    resultChannel.trySend(false).isSuccess
                }
            })

            resultChannel.receive()
        }
    }

    public fun signUpResult(): MutableLiveData<Boolean> {
        return signUpResult
    }


}
