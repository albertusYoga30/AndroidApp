package app.binar.synrgy.android.helloworld.data.api.signUp

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("name") var name : String,
    @SerializedName("email") var email : String,
    @SerializedName("job") var job : String,
    @SerializedName("password") var password : String
)
