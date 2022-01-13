package app.binar.synrgy.android.helloworld.ui.homePage

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synrgy.android.helloworld.R
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.model.home.*
import java.security.acl.Group

class HomeViewModel(val sharedPreference: SharedPreferences) : ViewModel() {
    val data: MutableLiveData<String> = MutableLiveData("contoh ")
    val homeModel: MutableLiveData<HomeModel> = MutableLiveData()

    private var _homeModel: HomeModel? = null

    fun onViewLoaded() {
        val profile = ProfileModel(
            image = sharedPreference.getString(Const.IMAGE,"")?:"",
            name = sharedPreference.getString(Const.FULLNAME, "") ?: "",
            job = sharedPreference.getString(Const.JOB, "") ?: "",
        )

        val consultation = listOf(
            ConsultationModel(
                icon = R.drawable.icon_dokter_umum,
                title = R.string.dokter_umum
            ),
            ConsultationModel(
                icon = R.drawable.icon_psikiater,
                title = R.string.psikiater
            ),
            ConsultationModel(
                icon = R.drawable.icon_dokter_obat,
                title = R.string.dokter_obat
            ),
            ConsultationModel(
                icon = R.drawable.icon_dokter_obat,
                title = R.string.dokter_anak
            ),
            ConsultationModel(
                icon = R.drawable.icon_dokter_obat,
                title = R.string.dokter_mata
            )

        )

        val topRatedDoctors = listOf(
            TopRatedDoctorModel(
                icon = R.drawable.doctor1,
                name = R.string.doctor1_name,
                specialist = R.string.doctor1_specialist,
                rating = 5,
                isOnline= false
            ),
            TopRatedDoctorModel(
                icon = R.drawable.doctor2,
                name = R.string.doctor2_name,
                specialist = R.string.doctor2_specialist,
                rating = R.string.rating5,
                isOnline= true

            ),
            TopRatedDoctorModel(
                icon = R.drawable.doctor3,
                name = R.string.doctor3_name,
                specialist = R.string.doctor3_specialist,
                rating = 5,
                isOnline= false

            )

        )

        val goodNews = listOf(
            GoodNewsModel(
                title = R.string.news1,
                icon = R.drawable.icon_good_news1

            ),
            GoodNewsModel(
                title = R.string.news2,
                icon = R.drawable.icon_good_news2

            ),
            GoodNewsModel(
                title = R.string.news3,
                icon = R.drawable.icon_good_news3

            )
        )



        _homeModel = HomeModel(
            profile = profile,
            consultationList = consultation,
            topRatedDoctorsList = topRatedDoctors,
            goodNewsList = goodNews
        )

        homeModel.value = _homeModel
    }


}