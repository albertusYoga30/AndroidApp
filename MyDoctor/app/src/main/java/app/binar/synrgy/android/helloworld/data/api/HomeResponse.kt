package app.binar.synrgy.android.helloworld.data.api

import app.binar.synrgy.android.helloworld.model.home.TopRatedDoctorModel
import app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor.AdapterGoodNews
import com.google.gson.annotations.SerializedName

//data dari api
data class HomeResponse(
    @SerializedName("consultations") val consultations: List<ConsultationResponse>,
    @SerializedName("top_rated_doctor") val topRatedDoctor: List<TopRatedDoctorResponse>,
    @SerializedName("good_news") val goodNews: List<GoodNewsResponse>
) {
    data class ConsultationResponse(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String,
        @SerializedName("icon") val icon: String
    )

    data class TopRatedDoctorResponse(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("image") val image: String,
        @SerializedName("specialist") val specialist: String,
        @SerializedName("rating") val rating: Float,
        @SerializedName("is_online") val isOnline: Boolean
    )

    data class GoodNewsResponse(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String,
        @SerializedName("image") val image: String,
        @SerializedName("date") val date: String
    )
}
