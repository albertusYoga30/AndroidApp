package app.binar.synrgy.android.helloworld.data.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ImageAPI {
    companion object {
        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.imgbb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @POST("1/upload")
    @Multipart
    suspend fun uploadImage(
        @Query("expiration") expiration: Int = 100,
        @Query("key") key: String = "3ef43fdb06a18016d0dc1d2104f58a23",
        @Part image: MultipartBody.Part
    ): Response<ImageDataResponse>
}