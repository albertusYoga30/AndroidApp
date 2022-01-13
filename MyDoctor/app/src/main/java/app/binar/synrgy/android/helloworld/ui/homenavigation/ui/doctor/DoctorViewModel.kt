package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synrgy.android.helloworld.R
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.data.api.HomeAPI
import app.binar.synrgy.android.helloworld.data.api.HomeResponse
import app.binar.synrgy.android.helloworld.model.home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DoctorViewModel(val sharedPreference: SharedPreferences?) : ViewModel() {
    val homeModel: MutableLiveData<HomeResponse> = MutableLiveData()
    val profileModel: MutableLiveData<ProfileModel> = MutableLiveData()

    private lateinit var homeAPI: HomeAPI

    private var _homeModel: HomeModel? = null

    fun onViewLoaded() {
        val profile = ProfileModel(
            image = sharedPreference?.getString(Const.IMAGE, "") ?: "",
            name = sharedPreference?.getString(Const.FULLNAME, "") ?: "",
            job = sharedPreference?.getString(Const.JOB, "") ?: "",
        )

        profileModel.value = profile
        _homeModel = HomeModel(profile = profile)

        getDataFromAPI()

//        homeModel.value = _homeModel


//        val consultation = listOf(
//            ConsultationModel(
//                icon = R.drawable.icon_dokter_umum,
//                title = R.string.dokter_umum
//            ),
//            ConsultationModel(
//                icon = R.drawable.icon_psikiater,
//                title = R.string.psikiater
//            ),
//            ConsultationModel(
//                icon = R.drawable.icon_dokter_obat,
//                title = R.string.dokter_obat
//            ),
//            ConsultationModel(
//                icon = R.drawable.icon_dokter_obat,
//                title = R.string.dokter_anak
//            ),
//            ConsultationModel(
//                icon = R.drawable.icon_dokter_obat,
//                title = R.string.dokter_mata
//            )
//
//        )
//
//        val topRatedDoctors = listOf(
//            TopRatedDoctorModel(
//                icon = R.drawable.doctor1,
//                name = R.string.doctor1_name,
//                specialist = R.string.doctor1_specialist,
//                rating = 5,
//                isOnline = false
//            ),
//            TopRatedDoctorModel(
//                icon = R.drawable.doctor2,
//                name = R.string.doctor2_name,
//                specialist = R.string.doctor2_specialist,
//                rating = R.string.rating5,
//                isOnline = true
//
//            ),
//            TopRatedDoctorModel(
//                icon = R.drawable.doctor3,
//                name = R.string.doctor3_name,
//                specialist = R.string.doctor3_specialist,
//                rating = 5,
//                isOnline = false
//
//            )
//
//        )
//
//        val goodNews = listOf(
//            GoodNewsModel(
//                title = R.string.news1,
//                icon = R.drawable.icon_good_news1
//
//            ),
//            GoodNewsModel(
//                title = R.string.news2,
//                icon = R.drawable.icon_good_news2
//
//            ),
//            GoodNewsModel(
//                title = R.string.news3,
//                icon = R.drawable.icon_good_news3
//
//            )
//        )


//
//        _homeModel = HomeModel(
//            profile = profile,
//            consultationList = consultation,
//            topRatedDoctorsList = topRatedDoctors,
//            goodNewsList = goodNews
//        )
//
//        homeModel.value = _homeModel
    }


    fun getDataFromAPI() {
        homeAPI = HomeAPI.getInstanceApiary().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getPageDoctor()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("getDataFromAPI()", response.body().toString())
                    println("hasil sukses atau panggil ke api")
                    homeModel.value = response.body()
                } else {
                    Log.d("getDataFromAPI()", response.body().toString())
                    println("gagal get ke api")
                }
            }
        }
    }
}