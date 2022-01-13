package app.binar.synrgy.android.helloworld.data.api.signIn

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("login") var login : String,
    @SerializedName("password") var password : String

)
