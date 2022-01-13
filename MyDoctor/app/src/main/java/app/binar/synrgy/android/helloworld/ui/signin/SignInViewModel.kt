package app.binar.synrgy.android.helloworld.ui.signin

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.data.api.HomeAPI
import app.binar.synrgy.android.helloworld.data.api.signIn.SignInRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

    val isButtonEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    val goToHomePage: MutableLiveData<Boolean> = MutableLiveData(false)
    val showErrorMessage: MutableLiveData<String> = MutableLiveData()

    private lateinit var homeAPI: HomeAPI
    private var email: String = ""
    private var password: String = ""

    fun onChangeEmail(email: String) {
        this.email = email
        validateInput()
    }

    fun onChangePassword(password: String) {
        this.password = password
        validateInput()
    }

    //untuk mendisable button
    fun validateInput() {
        isButtonEnable.value = email.isNotEmpty() && password.isNotEmpty()
    }

    fun doSignIn() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val request = SignInRequest(
                login = email,
                password = password
            )

            val response = homeAPI.postSignIn(request = request)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val signInResponse = response.body()

                    sharedPreferences.edit {
                        this.putBoolean(Const.IS_LOGIN, true)
                        this.putString(Const.TOKEN, signInResponse?.token)
                        this.putString(Const.USER_ID, signInResponse?.id)

                        this.putString(Const.EMAIL, signInResponse?.email)
                        this.putString(Const.FULLNAME, signInResponse?.name)
                        this.putString(Const.JOB, signInResponse?.job)
                        this.putString(Const.IMAGE, signInResponse?.image)

                        apply()
                    }
                    goToHomePage.value = true

                } else {
                    showErrorMessage.value = response.errorBody()?.string()

                }
            }

        }
    }
}