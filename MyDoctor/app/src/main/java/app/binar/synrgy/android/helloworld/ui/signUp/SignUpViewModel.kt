package app.binar.synrgy.android.helloworld.ui.signUp

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.data.api.HomeAPI
import app.binar.synrgy.android.helloworld.data.api.signUp.SignUpRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(val sharedPreferences: SharedPreferences) : ViewModel() {

    private lateinit var homeAPI: HomeAPI

    var name: String = ""
    var email: String = ""
    var password: String = ""
    var job: String = ""

    val isButtonEnable: MutableLiveData<Boolean> = MutableLiveData(false)
//    val goToUploadImage: MutableLiveData<Boolean> = MutableLiveData(false)
    val showErrorMessage: MutableLiveData<String> = MutableLiveData()
    val goToLogin: MutableLiveData<Boolean> = MutableLiveData(false)

    fun validateInput() {
        isButtonEnable.value = name.isNotEmpty() &&
                email.isNotEmpty() && password.isNotEmpty() && job.isNotEmpty()
        //logic pattern validate here
    }

    fun onChangeName(name: String) {
        this.name = name
        validateInput()
    }

    fun onChangeEmail(email: String) {
        this.email = email
        validateInput()
    }

    fun onChangePassword(password: String) {
        this.password = password
        validateInput()
    }

    fun onChangeJob(job: String) {
        this.job = job
        validateInput()
    }

    fun doSignUp() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val request = SignUpRequest(
                name = name,
                email = email,
                password = password,
                job = job
            )
            val response = homeAPI.postSignUp(request)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val signInResponse = response.body()

//                    val responseEmail = response.body()?.email
//                    val responsePassword = response.body()?.password
//                    val signInResponse = response.body()

//                    sharedPreferences.edit {
//                        this.putBoolean(Const.IS_LOGIN, true)
//                        this.putString(Const.TOKEN, signInResponse?.token)
//                        this.putString(Const.USER_TYPE, signInResponse?.userType)
//
//                        this.putString(Const.EMAIL, signInResponse?.email)
//                        this.putString(Const.PASSWORD, signInResponse?.password)
//                        this.putString(Const.FULLNAME, signInResponse?.name)
//                        this.putString(Const.JOB, signInResponse?.job)
//                        this.putString(Const.IMAGE, signInResponse?.image)
//
//                        apply()
//                    }

//                    goToUploadImage.value = true

                    //karena ada perubahan dari API jadi dari flownya akan kemblai kehalama login
                    //setelah sign UP

                    goToLogin.value = true

                } else {
                    showErrorMessage.value = response.errorBody()?.string()
                }
            }
        }
    }
}