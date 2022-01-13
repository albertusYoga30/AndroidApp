package app.binar.synrgy.android.helloworld.data.api

import com.google.gson.annotations.SerializedName

data class HospitalListResponse(
    @SerializedName("hospital") val hospital: List<HospitalResponse>

) {
    data class HospitalResponse(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String,
        @SerializedName("image") val image: String,
        @SerializedName("address") val address: String
    )

}




