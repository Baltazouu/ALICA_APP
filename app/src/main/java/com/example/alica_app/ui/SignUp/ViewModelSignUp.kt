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
    val errorMessage = MutableLiveData<String>()

    fun signUp(firstName: String, lastName: String, emailAddress: String, password: String) {
        if (validateFields(firstName, lastName, emailAddress, password)) {
            val signUpBody = SignUpBody(firstName, lastName, emailAddress, password)

            service.signUp(signUpBody).enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        // La demande a réussi
                        Log.i("SignUp", "Sign up successful")
                        signUpResult.value = true
                    } else {
                        // La demande a échoué
                        Log.e("SignUp", "Sign up failed")
                        signUpResult.value = false
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    // La demande a échoué à cause d'une erreur réseau ou autre
                    Log.e("SignUp", "Sign up failure", t)
                    signUpResult.value = false
                }
            })
        }
    }

    private fun validateFields(firstName: String, lastName: String, emailAddress: String, password: String): Boolean {
        if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || password.isEmpty()) {
            errorMessage.value = "All fields are required."
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            errorMessage.value = "Invalid email address."
            return false
        }

        if (password.length < 6) {
            errorMessage.value = "Password must be at least 6 characters long."
            return false
        }

        return true
    }

    public fun getSignUpResult(): MutableLiveData<Boolean> {
        return signUpResult
    }
}
