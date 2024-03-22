import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alica_app.data.models.SignUpBody
import com.example.alica_app.data.services.SignUpService
import com.example.alica_app.data.services.createSignUpRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ViewModelSignUp : ViewModel() {


    private val service = createSignUpRetrofit().create(SignUpService::class.java)
    val signUpResult = MutableLiveData<Boolean>()

    // Modifiez la méthode signUp() pour qu'elle utilise un canal pour signaler la fin de son exécution
    suspend fun signUp(firstName: String, lastName: String, emailAddress: String, password: String): Boolean {
        val signUpBody = SignUpBody(firstName, lastName, emailAddress, password)
        val channel = Channel<Boolean>()

        service.signUp(signUpBody).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    // La demande a réussi
                    Log.i("CHILD", "Sign up successful")
                    CoroutineScope(Dispatchers.Default).launch {
                        channel.send(true) // Envoyer le résultat à travers le canal
                    }
                } else {
                    // La demande a échoué
                    Log.e("CHILD", "Sign up failed")
                    CoroutineScope(Dispatchers.Default).launch {
                        channel.send(false) // Envoyer le résultat à travers le canal
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                // La demande a échoué à cause d'une erreur réseau ou autre
                Log.e("CHILD", "Sign up failure", t)
                CoroutineScope(Dispatchers.Default).launch {
                    channel.send(false) // Envoyer le résultat à travers le canal
                }
            }
        })

        // Attendre que la méthode signUp mette à jour signUpResult et retourner le résultat
        return channel.receive()
    }



    public fun signUpResult(): MutableLiveData<Boolean> {
        return signUpResult
    }


}
