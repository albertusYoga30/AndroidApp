package app.binar.synrgy.android.helloworld.data.api.signIn

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("user-token") var token: String,
    @SerializedName("objectId") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("job") var job: String,
    @SerializedName("image") var image: String

)