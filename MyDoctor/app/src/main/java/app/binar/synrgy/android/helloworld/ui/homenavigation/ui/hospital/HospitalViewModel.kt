package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.hospital

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.data.api.HomeAPI
import app.binar.synrgy.android.helloworld.data.api.HospitalListResponse
import app.binar.synrgy.android.helloworld.data.local.HomeDAO
import app.binar.synrgy.android.helloworld.data.local.HospitalCache
import app.binar.synrgy.android.helloworld.database.AppDatabase
import app.binar.synrgy.android.helloworld.model.home.ProfileModel
import app.binar.synrgy.android.helloworld.model.hospital.HospitalModel
import kotlinx.coroutines.*
import kotlin.math.log

class HospitalViewModel(private val appDatabase: AppDatabase) : ViewModel() {
    val hospitalModel:MutableLiveData<List<HospitalModel>> = MutableLiveData()
    private lateinit var homeAPI: HomeAPI

    fun onViewLoaded(){
        getDataFromAPI()
    }

    fun getDataFromAPI() {
        homeAPI = HomeAPI.getInstanceApiary().create(HomeAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch{

            val response =homeAPI.getPageHospital()

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    Log.d("getPageHospital()", response.body().toString())
//                    hospitalModel.value =response.body()
//                    println("sukses panggil ke API")

                    insertHospital(response.body())

                }else{
                    Log.d("getPageHospital()",response.body().toString())
                    println("gagal memanggil API")
                }
            }
        }
    }

    fun insertHospital(response: HospitalListResponse?){
        response?.let {
            CoroutineScope(Dispatchers.IO).launch {
                val hospitals:List<HospitalCache> = it.hospital.map { hospital->
                    HospitalCache(
                        id = hospital.id,
                        title = hospital.title,
                        image = hospital.image,
                        address = hospital.address
                    )
                }

                appDatabase.homeDAO().insertAllHospital(*hospitals.toTypedArray())

                val hospitalCaches: List<HospitalCache> = appDatabase.homeDAO().getAllHospital()
                withContext(Dispatchers.Main){
                    hospitalModel.value = hospitalCaches.map {
                        it.toModel()
                    }
                }
            }
        }
    }
}