package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synrgy.android.helloworld.data.api.HomeResponse
import app.binar.synrgy.android.helloworld.databinding.AdapterTopRateDoctorBinding
import app.binar.synrgy.android.helloworld.model.home.TopRatedDoctorModel
import com.bumptech.glide.Glide

class AdapterTopRatedDoctors(
    var data: List<HomeResponse.TopRatedDoctorResponse>) :

    RecyclerView.Adapter<AdapterTopRatedDoctors.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterTopRateDoctorBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(topRateDoctors: HomeResponse.TopRatedDoctorResponse){
//
//            binding.iconDoctor.setImageResource(topRateDoctors.icon)
//            binding.doctorName.setText(topRateDoctors.name)
//            binding.doctorSpecialist.setText(topRateDoctors.specialist)
//            binding.doctorRate.setRating(topRateDoctors.rating.toFloat())


            Glide.with(binding.root)
                .load(topRateDoctors.image)
                .into(binding.iconDoctor)

            binding.doctorName.text = topRateDoctors.name
            binding.doctorSpecialist.text = topRateDoctors.specialist
            binding.doctorRate.rating = topRateDoctors.rating

            binding.imageOnline.visibility = if (topRateDoctors.isOnline) {
                View.VISIBLE
            }else {
                View.GONE
            }
        }
    }

    fun update(data:List<HomeResponse.TopRatedDoctorResponse>){
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTopRateDoctorBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}