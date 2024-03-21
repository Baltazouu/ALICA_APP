import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alica_app.data.models.SignUpBody
import com.example.alica_app.data.services.SignUpService
import com.example.alica_app.data.services.createSignUpRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelSignUp : ViewModel() {

    private val service = createSignUpRetrofit().create(SignUpService::class.java)
    val signUpResult = MutableLiveData<Boolean>()

    fun signUp(firstName: String, lastName: String, emailAddress: String, password: String) {
        val signUpBody = SignUpBody(firstName, lastName, emailAddress, password)

        service.signUp(signUpBody).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    // La demande a réussi
                    Log.i("SUCCESS","SUCCESS")
                    signUpResult.value = true
                } else {
                    // La demande a échoué
                    Log.i("FAILED","FAILED")
                    signUpResult.value = false
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                // La demande a échoué à cause d'une erreur réseau ou autre

                Log.i("FAILURE",t.message.toString())
                Log.i("FAILURE","FAILURE")
                signUpResult.value = false
            }
        })
    }
}
