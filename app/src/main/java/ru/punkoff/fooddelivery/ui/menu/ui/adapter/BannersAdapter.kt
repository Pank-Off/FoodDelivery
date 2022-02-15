package ru.punkoff.fooddelivery.ui.menu.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.punkoff.fooddelivery.databinding.ItemBannerBinding

class BannersAdapter : RecyclerView.Adapter<BannersAdapter.BannersViewHolder>() {

    private var data = listOf<Drawable?>()

    fun setData(data: List<Drawable?>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        return BannersViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    inner class BannersViewHolder(
        parent: ViewGroup, private val binding: ItemBannerBinding = ItemBannerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.root.background = data[position]
        }
    }
}