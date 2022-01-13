package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synrgy.android.helloworld.data.api.HomeResponse
import app.binar.synrgy.android.helloworld.databinding.AdapterGoodNewsBinding
import app.binar.synrgy.android.helloworld.model.home.GoodNewsModel
import com.bumptech.glide.Glide

class AdapterGoodNews(var data: List<HomeResponse.GoodNewsResponse>) :
    RecyclerView.Adapter<AdapterGoodNews.ViewHolder>() {
    inner class ViewHolder(val binding: AdapterGoodNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(goodNews: HomeResponse.GoodNewsResponse){
//                binding.goodNews.setText(goodNews.title)
//                binding.iconGoodNews.setImageResource(goodNews.icon)
                binding.goodNews.text = goodNews.title

                Glide.with(binding.root)
                    .load(goodNews.image)
                    .into(binding.iconGoodNews)
            }
    }

    fun update(data:List<HomeResponse.GoodNewsResponse>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterGoodNewsBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}