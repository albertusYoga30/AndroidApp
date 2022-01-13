package app.binar.synrgy.android.helloworld.data.api.updateProfile

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(
    @SerializedName("name") var name : String,
    @SerializedName("email") var email : String,
    @SerializedName("job") var job : String,
    @SerializedName("image") var image : String
)
