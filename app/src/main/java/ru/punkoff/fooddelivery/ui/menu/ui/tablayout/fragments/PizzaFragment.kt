package ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.punkoff.fooddelivery.R
import ru.punkoff.fooddelivery.databinding.FragmentPizzaBinding
import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import ru.punkoff.fooddelivery.ui.menu.ui.adapter.OnItemClickListener
import ru.punkoff.fooddelivery.ui.menu.ui.adapter.MenuAdapter
import ru.punkoff.fooddelivery.ui.menu.viewmodels.MenuViewModel

@AndroidEntryPoint
class PizzaFragment : Fragment() {

    private var _binding: FragmentPizzaBinding? = null
    private val menuAdapter = MenuAdapter()
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
                MenuViewState.Loading -> menuAdapter.loadingData()

                is MenuViewState.Success -> {
                    /**
                     * Убрать Handler перед отправкой!!!
                     */
                    Handler(Looper.myLooper()!!).postDelayed({
                        Log.e(javaClass.simpleName, it.items.toString())
                        menuAdapter.setData(it.items)
                    }, 3000)
                }
            }
        }
        with(binding) {
            pizzaRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            menuAdapter.attachListener(object : OnItemClickListener {
                override fun onClick(model: FoodModel) {
                    Log.e(javaClass.simpleName, model.toString())
                    Snackbar.make(view, getString(R.string.add_to_cart), Snackbar.LENGTH_SHORT)
                        .setAction(
                            "Ок"
                        ) { }.show()
                    viewModel.insertToCart(model)
                }

            })
            pizzaRecycler.adapter = menuAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}