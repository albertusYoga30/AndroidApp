package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.data.api.HomeResponse
import app.binar.synrgy.android.helloworld.databinding.FragmentDoctorBinding
import app.binar.synrgy.android.helloworld.ui.profile.ProfileActivity
import com.bumptech.glide.Glide

class DoctorFragment : Fragment() {

    private lateinit var viewModel: DoctorViewModel
    private lateinit var binding: FragmentDoctorBinding
//    private var _binding: FragmentDoctorBinding? = null
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        viewModel = DoctorViewModel()
        binding = FragmentDoctorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharedPreferences =
            activity?.getSharedPreferences(Const.PREF_NAME, AppCompatActivity.MODE_PRIVATE)
        viewModel = DoctorViewModel(sharedPreferences)


        //profile
        viewModel.profileModel.observe(viewLifecycleOwner, {
            Glide.with(this).load(it.image).circleCrop().into(binding.imageUser)
            binding.textName.text = it.name
            binding.textJob.text = it.job
        })

        binding.imageUser.setOnClickListener {
            startActivity(Intent(this.context, ProfileActivity::class.java))
        }

        //consultation
        val adapterConsultation = AdapterConsultation(listOf(),
            object : AdapterConsultation.EventListener {
                override fun click(item: HomeResponse.ConsultationResponse) {
                    Toast.makeText(
                        this@DoctorFragment.context,
                        "item.title",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        binding.recyclerConsultation.adapter = adapterConsultation

        //top rated doctor
        val adapterTopRatedDoctors = AdapterTopRatedDoctors(listOf())
        binding.recyclerTopRateDoctor.adapter = adapterTopRatedDoctors

        //good news
        val adapterGoodNews = AdapterGoodNews(listOf())
        binding.recycleGoodNews.adapter = adapterGoodNews

        viewModel.onViewLoaded()

        viewModel.homeModel.observe(viewLifecycleOwner, {
            adapterConsultation.update(it.consultations)
            adapterTopRatedDoctors.update(it.topRatedDoctor)
            adapterGoodNews.update(it.goodNews)

        })

        return root
    }
}