package ru.punkoff.fooddelivery.ui.menu.ui.tablayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.punkoff.fooddelivery.databinding.ItemTabBinding


class TabsAdapter : RecyclerView.Adapter<TabsAdapter.TabsViewHolder>() {

    private var data = listOf<String>()

    private lateinit var listener: OnTabClickListener
    fun attachListener(listener: OnTabClickListener) {
        this.listener = listener
    }

    fun setData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabsViewHolder {
        return TabsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TabsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    inner class TabsViewHolder(
        parent: ViewGroup, private val binding: ItemTabBinding = ItemTabBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tab.text = data[position]
            binding.tab.setOnClickListener { listener.onClick(position) }
        }
    }
}