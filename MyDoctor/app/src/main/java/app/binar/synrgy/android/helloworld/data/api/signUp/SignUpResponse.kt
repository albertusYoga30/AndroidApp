package app.binar.synrgy.android.helloworld.data.api.signUp

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
//    @SerializedName("token") var token: String,
//    @SerializedName("user_type") var userType: String,
//    @SerializedName("name") var name: String,
//    @SerializedName("email") var email: String,
//    @SerializedName("job") var job: String,
//    @SerializedName("image") var image: String,
//    @SerializedName("password") var password: String
    @SerializedName("name") var name : String,
    @SerializedName("email") var email : String,
    @SerializedName("job") var job : String,
    @SerializedName("password") var password : String
)