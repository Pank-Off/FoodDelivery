package ru.punkoff.fooddelivery.ui.menu.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import ru.punkoff.fooddelivery.R
import ru.punkoff.fooddelivery.databinding.FragmentMenuBinding
import ru.punkoff.fooddelivery.ui.menu.MenuViewModel
import ru.punkoff.fooddelivery.ui.menu.ui.adapter.BannersAdapter
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.FragmentTypeEnum
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.OnTabClickListener
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.PagerAdapter
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.TabsAdapter
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments.ComboFragment
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments.DessertsFragment
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments.DrinksFragment
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments.PizzaFragment

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    private val bannersAdapter = BannersAdapter()
    private val tabsAdapter = TabsAdapter()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(MenuViewModel::class.java)

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(R.id.container, PizzaFragment()).commit()
        setupBanners()
        setupTabs()

    }

    private fun setupTabs() {
        with(binding) {
            tabsRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            tabsAdapter.setData(
                listOf(
                    getString(R.string.pizza),
                    getString(R.string.combo),
                    getString(R.string.desserts),
                    getString(R.string.drinks)
                )
            )

            tabsAdapter.attachListener(object : OnTabClickListener {
                override fun onClick(position: Int) {
                    when (FragmentTypeEnum.values()[position]) {
                        FragmentTypeEnum.PIZZA -> {
                            childFragmentManager.beginTransaction()
                                .replace(R.id.container, PizzaFragment()).commit()
                        }
                        FragmentTypeEnum.COMBO -> childFragmentManager.beginTransaction()
                            .replace(R.id.container, ComboFragment()).commit()
                        FragmentTypeEnum.DESSERTS -> childFragmentManager.beginTransaction()
                            .replace(R.id.container, DessertsFragment()).commit()
                        FragmentTypeEnum.DRINKS ->childFragmentManager.beginTransaction()
                            .replace(R.id.container, DrinksFragment()).commit()
                    }
                }

            })
            tabsRecycler.adapter = tabsAdapter

        }
    }

    private fun setupBanners() {
        with(binding) {
            bannersRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            bannersAdapter.setData(
                listOf(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.banners_item
                    ),
                    ContextCompat.getDrawable(requireContext(), R.drawable.banners_item),
                    ContextCompat.getDrawable(requireContext(), R.drawable.banners_item),
                    ContextCompat.getDrawable(requireContext(), R.drawable.banners_item),
                    ContextCompat.getDrawable(requireContext(), R.drawable.banners_item)
                )
            )

            bannersRecycler.adapter = bannersAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}