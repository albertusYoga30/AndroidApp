package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synrgy.android.helloworld.data.api.HomeResponse
import app.binar.synrgy.android.helloworld.databinding.AdapterConsultationBinding
import app.binar.synrgy.android.helloworld.model.home.ConsultationModel
import com.bumptech.glide.Glide

class AdapterConsultation(
    var data: List<HomeResponse.ConsultationResponse>,
    val listener: EventListener
    ) :

    RecyclerView.Adapter<AdapterConsultation.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterConsultationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(consultation: HomeResponse.ConsultationResponse) {
            Glide.with(binding.root)
                .load(consultation.icon)
                .into(binding.imageIcon)

            binding.textTitle.text = consultation.title
            binding.root.setOnClickListener {
                listener.click(consultation)
            }
        }

    }

    fun update(data: List<HomeResponse.ConsultationResponse>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterConsultationBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

        //cara ke 2 menampilkan toast di recycle view
//        val listText =holder.binding.root.context.getString(data[position].title)
//        holder.itemView.setOnClickListener{
//            Snackbar.make(it, "$listText",
//                Snackbar.LENGTH_LONG
//            ).show()
//        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener{
        fun click(item:HomeResponse.ConsultationResponse)
    }
}