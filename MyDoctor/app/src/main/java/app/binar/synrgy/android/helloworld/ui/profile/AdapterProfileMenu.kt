package app.binar.synrgy.android.helloworld.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synrgy.android.helloworld.databinding.AdapterProfileMenuBinding
import app.binar.synrgy.android.helloworld.model.home.ProfileMenuModel

class AdapterProfileMenu(
    var data: List<ProfileMenuModel>,
    val listener: EventListener
) :
    RecyclerView.Adapter<AdapterProfileMenu.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterProfileMenuBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(profileMenu: ProfileMenuModel){
            binding.iconEditprofile.setImageResource(profileMenu.icon)
            binding.titleEditProfile.setText(profileMenu.title)
            binding.subtitleEditProfile.setText(profileMenu.subtitle)
            binding.root.setOnClickListener {
                listener.click(profileMenu)
            }
        }


    }
    fun update(data: List<ProfileMenuModel>){
        this.data =data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterProfileMenuBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener{
        fun click(item:ProfileMenuModel)
    }

}