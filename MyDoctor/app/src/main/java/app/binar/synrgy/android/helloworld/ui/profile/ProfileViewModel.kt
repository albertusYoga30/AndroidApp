package app.binar.synrgy.android.helloworld.ui.profile

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synrgy.android.helloworld.R
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.data.api.HomeAPI
import app.binar.synrgy.android.helloworld.data.api.ImageAPI
import app.binar.synrgy.android.helloworld.data.api.updateProfile.UpdateProfileRequest
import app.binar.synrgy.android.helloworld.model.home.HomeModel
import app.binar.synrgy.android.helloworld.model.home.ProfileMenuModel
import app.binar.synrgy.android.helloworld.model.home.ProfileModel
import kotlinx.coroutines.*
import okhttp3.MultipartBody

class ProfileViewModel(val sharedPreferences: SharedPreferences) : ViewModel() {
    val profileModel: MutableLiveData<ProfileModel> = MutableLiveData()
    val homeModel: MutableLiveData<HomeModel> = MutableLiveData()

    private var _homeModel: HomeModel? = null
    private lateinit var imageAPI:ImageAPI
    private lateinit var homeAPI:HomeAPI

    fun onViewLoaded() {
        val profile = ProfileModel(
            image = sharedPreferences.getString(Const.IMAGE, "") ?: "",
            name = sharedPreferences.getString(Const.FULLNAME, "") ?: "",
            job = sharedPreferences.getString(Const.JOB, "") ?: ""
        )

        val profileMenu = listOf(
            ProfileMenuModel(
                icon = R.drawable.ic_icon_profile_editprofile,
                title = R.string.title_editmenu_editprofile,
                subtitle = R.string.subtitle_editmenu_editprofile
            ),
            ProfileMenuModel(
                icon = R.drawable.ic_icon_profilemenu_language,
                title = R.string.title_editmenu_language,
                subtitle = R.string.subtitle_editmenu_language
            ),
            ProfileMenuModel(
                icon = R.drawable.ic_icon_profile_editprofile,
                title = R.string.title_editmenu_giveUs_rate,
                subtitle = R.string.subtitle_editmenu_giveUs_rate
            ),
            ProfileMenuModel(
                icon = R.drawable.ic_icon_profile_editprofile,
                title = R.string.title_editmenu_help_center,
                subtitle = R.string.subtitle_editmenu_help_center
            )

        )
        profileModel.value = profile

        _homeModel = HomeModel(
            profile = profile,
            profileMenuList = profileMenu
        )

        homeModel.value = _homeModel


    }

    fun onChangedImage(value: String) {
        sharedPreferences.edit {
            putString(Const.IMAGE, value)
            apply()
        }

    }

    fun uploadImage(body: MultipartBody.Part) {
        imageAPI = ImageAPI.getInstance().create(ImageAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = imageAPI.uploadImage(image = body)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val image = response.body()?.data?.image?.url ?:""

                    onChangedImage(image)

                    val profile = ProfileModel(
                        image = image,
                        name = sharedPreferences.getString(Const.FULLNAME,"")?: "",
                        job = sharedPreferences.getString(Const.JOB,"")?: ""

                    )
                    profileModel.value = profile
                }
            }
        }
    }

    //cuma test api
    fun sample() {
        CoroutineScope(Dispatchers.IO).launch {
            println("test print")
            delay(3000)
            println("print after 3 second")
        }

    }
    fun updateProfile(image:String){
        homeAPI =HomeAPI.getInstance().create(HomeAPI::class.java)

        val token = sharedPreferences.getString(Const.TOKEN,"").orEmpty()
        val id = sharedPreferences.getString(Const.USER_ID,"").orEmpty()
        val request = UpdateProfileRequest(image = image)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.updateProfile(
                token=token,
                id=id,
                request = request
            )

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                   val updateProfile =response.body()

                   sharedPreferences.edit{
                       putString(Const.IMAGE,updateProfile?.image)
                       apply()
                   }
                }
            }
        }
    }

    //fun logout
}