package ru.punkoff.fooddelivery.ui.menu.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.punkoff.fooddelivery.databinding.ItemPizzaBinding
import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.utils.PicassoLoader

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.PizzaViewHolder>() {

    private var data = listOf<FoodModel>()

    private lateinit var onItemClickListener: OnItemClickListener
    private var isShimmer = true

    fun attachListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun loadingData() {
        isShimmer = true
        notifyDataSetChanged()
    }

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
            holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int = if (isShimmer) 5 else data.size

    inner class PizzaViewHolder(
        parent: ViewGroup, private val binding: ItemPizzaBinding = ItemPizzaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FoodModel) {
            with(binding) {
                shimmer.stopShimmer()
                shimmer.setShimmer(null)
                shimmer.isClickable = true
                priceBtn.isClickable = true
                PicassoLoader.loadImage(item.imageUrl, image)
                title.text = item.title
                description.text = item.description
                priceBtn.text = "от ${item.price} р"

                priceBtn.setOnClickListener {
                    if (::onItemClickListener.isInitialized) {
                        onItemClickListener.onClick(item)
                    }
                }
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