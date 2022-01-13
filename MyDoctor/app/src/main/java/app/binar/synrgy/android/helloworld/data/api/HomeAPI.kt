package app.binar.synrgy.android.helloworld.data.api

import app.binar.synrgy.android.helloworld.data.api.signIn.SignInRequest
import app.binar.synrgy.android.helloworld.data.api.signIn.SignInResponse
import app.binar.synrgy.android.helloworld.data.api.signUp.SignUpRequest
import app.binar.synrgy.android.helloworld.data.api.signUp.SignUpResponse
import app.binar.synrgy.android.helloworld.data.api.updateProfile.UpdateProfileRequest
import app.binar.synrgy.android.helloworld.data.api.updateProfile.UpdateProfileResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface HomeAPI {

    companion object {
        fun getInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://silkybranch.backendless.app/api/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun getInstanceApiary(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://private-anon-019e52e73b-mydoctorexample.apiary-mock.com/api/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

    @GET("doctor")
    suspend fun getPageDoctor(): Response<HomeResponse>

    @GET("hospital")
    suspend fun getPageHospital(): Response<HospitalListResponse>

    @GET("message")
    suspend fun getPageMessage(): Response<MessageListResponse>

    @POST("users/login")
    suspend fun postSignIn(@Body request: SignInRequest
    ): Response<SignInResponse>

    @POST("users/register")
    suspend fun postSignUp(@Body request: SignUpRequest
    ): Response<SignUpResponse>

    @PUT("users/{id}")
    suspend fun updateProfile(
        @Header("user-token") token:String,
        @Path("id")id:String,
        @Body request:UpdateProfileRequest
    ):Response<UpdateProfileResponse>

}