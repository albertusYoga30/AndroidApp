package app.binar.synrgy.android.helloworld.ui.homePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import app.binar.synrgy.android.helloworld.R
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.data.api.HomeResponse
import app.binar.synrgy.android.helloworld.databinding.ActivityHomeBinding
import app.binar.synrgy.android.helloworld.model.home.ConsultationModel
import app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor.AdapterConsultation
import app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor.AdapterGoodNews
import app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor.AdapterTopRatedDoctors
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)
        viewModel = HomeViewModel(sharedPreferences)

        //kita siapkan untuk recycle connect
//        val consultationAdapter = AdapterConsultation(listOf(),
//            object : AdapterConsultation.EventListener{
//                override fun click(item:HomeResponse.ConsultationResponse){
//                    Toast.makeText(this@HomeActivity,getString(item.title),Toast.LENGTH_SHORT).show()
//                }
//            })
//        binding.recyclerConsultation.adapter = consultationAdapter

        val topRatedDoctorsAdapter = AdapterTopRatedDoctors(listOf())
        binding.recyclerTopRateDoctor.adapter = topRatedDoctorsAdapter

        val goodNewsAdapter = AdapterGoodNews(listOf())
        binding.recycleGoodNews.adapter =goodNewsAdapter

        viewModel.onViewLoaded()

        viewModel.data.observe(this, {

        })
        viewModel.homeModel.observe(this, {
            Glide.with(this).load(it.profile).circleCrop().into(binding.imageUser)
            binding.textName.text = it.profile.name
            binding.textJob.text = it.profile.job

            //update value menggunakan recycle
//            consultationAdapter.update(it.consultationList)
//            topRatedDoctorsAdapter.update(it.topRatedDoctorsList)
//            goodNewsAdapter.update(it.goodNewsList)
        })
    }
}