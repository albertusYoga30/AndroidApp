package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synrgy.android.helloworld.data.api.MessageListResponse
import app.binar.synrgy.android.helloworld.databinding.AdapterMessagesBinding
import com.bumptech.glide.Glide

class AdapterMessage(
    var data: List<MessageListResponse.MessageResponse>,
    val listener: EventListener
) : RecyclerView.Adapter<AdapterMessage.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterMessagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: MessageListResponse.MessageResponse) {
            Glide.with(binding.root).load(message.image).into(binding.userImageMessage)

            binding.userNameMessage.text = message.name
            binding.message.text = message.message
        }
    }

    fun update(data: List<MessageListResponse.MessageResponse>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMessagesBinding.inflate(inflater)
        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener{
        fun click(item: MessageListResponse.MessageResponse)
    }

}