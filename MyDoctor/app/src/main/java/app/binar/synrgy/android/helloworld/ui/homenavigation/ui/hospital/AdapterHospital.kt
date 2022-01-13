package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.hospital

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synrgy.android.helloworld.data.api.HospitalListResponse
import app.binar.synrgy.android.helloworld.databinding.AdapterHospitalBinding
import app.binar.synrgy.android.helloworld.model.hospital.HospitalModel
import com.bumptech.glide.Glide

class AdapterHospital(
    var data: List<HospitalModel>,
    val listener: EventListener
    ) :

    RecyclerView.Adapter<AdapterHospital.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hospital: HospitalModel) {
            Glide.with(binding.root)
                .load(hospital.image)
                .into(binding.iconHospital)

            binding.hospitalName.text =hospital.title
            binding.hospitalLocation.text = hospital.address
            binding.root.setOnClickListener {
                listener.click(hospital)
            }
        }

    }

    fun update(data: List<HospitalModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterHospitalBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    interface EventListener{
        fun click(item: HospitalModel)
    }


}