package ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.punkoff.fooddelivery.databinding.FragmentPizzaBinding
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import ru.punkoff.fooddelivery.ui.menu.ui.adapter.PizzaAdapter
import ru.punkoff.fooddelivery.ui.menu.viewmodels.MenuViewModel

@AndroidEntryPoint
class PizzaFragment : Fragment() {

    private var _binding: FragmentPizzaBinding? = null
    private val pizzaAdapter = PizzaAdapter()
    private val binding get() = _binding!!

    private val viewModel: MenuViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPizzaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestData()

        viewModel.observeLiveData().observe(viewLifecycleOwner) {
            when (it) {
                MenuViewState.EMPTY -> {}
                MenuViewState.Loading -> TODO()
                is MenuViewState.Success -> {
                    Log.e(javaClass.simpleName, it.items.toString())
                    pizzaAdapter.setData(it.items)
                }
            }
        }
        with(binding) {
            pizzaRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            pizzaRecycler.adapter = pizzaAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}