package ru.punkoff.fooddelivery.ui.menu.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.punkoff.fooddelivery.databinding.ItemPizzaBinding
import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.utils.PicassoLoader

class PizzaAdapter : RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    private var data = listOf<FoodModel>()

    private var isShimmer = true
    fun setData(data: List<FoodModel>) {
        this.data = data
        isShimmer = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        return PizzaViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        if (isShimmer) {
            holder.startShimmer()
        } else {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int = if (isShimmer) 5 else data.size

    inner class PizzaViewHolder(
        parent: ViewGroup, private val binding: ItemPizzaBinding = ItemPizzaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                shimmer.stopShimmer()
                shimmer.setShimmer(null)
                shimmer.isClickable = true
                priceBtn.isClickable = true
                PicassoLoader.loadImage(data[position].imageUrl, image)
                title.text = data[position].title
                description.text = data[position].description
                priceBtn.text = "от ${data[position].price} р"
            }
        }

        fun startShimmer() {
            with(binding) {
                shimmer.startShimmer()
                shimmer.isClickable = false
                priceBtn.isClickable = false
            }
        }
    }
}