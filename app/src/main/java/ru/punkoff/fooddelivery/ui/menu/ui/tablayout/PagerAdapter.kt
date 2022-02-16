package ru.punkoff.fooddelivery.ui.menu.ui.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments.ComboFragment
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments.DessertsFragment
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments.DrinksFragment
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments.PizzaFragment

class PagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (FragmentTypeEnum.values()[getItemViewType(position)]) {
            FragmentTypeEnum.PIZZA -> PizzaFragment()
            FragmentTypeEnum.COMBO -> ComboFragment()
            FragmentTypeEnum.DESSERTS -> DessertsFragment()
            FragmentTypeEnum.DRINKS -> DrinksFragment()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> FragmentTypeEnum.PIZZA.ordinal
            1 -> FragmentTypeEnum.COMBO.ordinal
            2 -> FragmentTypeEnum.DESSERTS.ordinal
            else -> FragmentTypeEnum.DRINKS.ordinal
        }
    }

    override fun getItemCount(): Int = CARD_ITEM_SIZE

    companion object {
        private const val CARD_ITEM_SIZE = 4
    }
}