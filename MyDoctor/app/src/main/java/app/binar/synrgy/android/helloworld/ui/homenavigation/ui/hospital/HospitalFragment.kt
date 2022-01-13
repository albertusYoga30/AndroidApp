package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.binar.synrgy.android.helloworld.data.api.HospitalListResponse
import app.binar.synrgy.android.helloworld.database.AppDatabase
import app.binar.synrgy.android.helloworld.databinding.FragmentHospitalBinding
import app.binar.synrgy.android.helloworld.model.hospital.HospitalModel

class HospitalFragment : Fragment() {

    private lateinit var viewModel: HospitalViewModel
    private lateinit var binding: FragmentHospitalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appDatabase = AppDatabase.getInstance(requireContext())
        viewModel = HospitalViewModel(appDatabase)
        binding = FragmentHospitalBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapterHospital = AdapterHospital(listOf(),
            object : AdapterHospital.EventListener {
                override fun click(item: HospitalModel) {
                    Toast.makeText(
                        context, item.title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        binding.recicleviewHospital.adapter = adapterHospital
        viewModel.onViewLoaded()

        viewModel.hospitalModel.observe(viewLifecycleOwner, {
            adapterHospital.update(it)

//            val count = adapterHospital.itemCount
            binding.countHospital.text ="${it.count()} tersedia"
        })
        return root
    }
}