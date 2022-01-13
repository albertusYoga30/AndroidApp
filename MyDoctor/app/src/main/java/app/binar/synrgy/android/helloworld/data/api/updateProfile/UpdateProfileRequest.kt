package app.binar.synrgy.android.helloworld.data.api.updateProfile

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("name") var name : String? =null,
    @SerializedName("job") var job : String? =null,
    @SerializedName("image") var image : String? =null
)
