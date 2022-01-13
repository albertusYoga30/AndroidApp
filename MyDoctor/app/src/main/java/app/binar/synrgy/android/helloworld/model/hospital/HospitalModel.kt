package app.binar.synrgy.android.helloworld.model.hospital

import com.google.gson.annotations.SerializedName

data class HospitalModel(
    val id: String,
    val title: String,
    val image: String,
    val address: String
)