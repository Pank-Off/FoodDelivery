package ru.punkoff.fooddelivery.ui.menu.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.punkoff.fooddelivery.R
import ru.punkoff.fooddelivery.databinding.FragmentMenuBinding
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import ru.punkoff.fooddelivery.ui.menu.ui.adapter.BannersAdapter
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.FragmentTypeEnum
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.PagerAdapter
import ru.punkoff.fooddelivery.ui.menu.ui.tablayout.TabsAdapter
import ru.punkoff.fooddelivery.ui.menu.viewmodels.MenuViewModel

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    private val bannersAdapter = BannersAdapter()
    private val tabsAdapter = TabsAdapter()

    private val binding get() = _binding!!

    private val viewModel: MenuViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBanners()
        setupTabs()
        viewModel.requestData()

        viewModel.observeLiveData().observe(viewLifecycleOwner) {
            when (it) {
                MenuViewState.EMPTY -> {}
                MenuViewState.Loading -> TODO()
                is MenuViewState.Success -> Log.e(javaClass.simpleName, it.items[0].toString())
            }
        }
    }

    private fun setupTabs() {
        with(binding) {
            val pagerAdapter = PagerAdapter(requireActivity())
            viewpager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, viewpager) { tab, position ->

                when (FragmentTypeEnum.values()[pagerAdapter.getItemViewType(position)]) {
                    FragmentTypeEnum.PIZZA -> {
                        tab.text = getString(R.string.pizza)
                    }
                    FragmentTypeEnum.COMBO -> {
                        tab.text = getString(R.string.combo)
                    }

                    FragmentTypeEnum.DESSERTS -> tab.text = getString(R.string.desserts)
                    FragmentTypeEnum.DRINKS -> tab.text = getString(R.string.drinks)
                }
            }.attach()
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