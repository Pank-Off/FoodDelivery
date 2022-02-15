package ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.punkoff.fooddelivery.R
import ru.punkoff.fooddelivery.databinding.FragmentPizzaBinding
import ru.punkoff.fooddelivery.model.PizzaModel
import ru.punkoff.fooddelivery.ui.menu.ui.adapter.PizzaAdapter

class PizzaFragment : Fragment() {

    private var _binding: FragmentPizzaBinding? = null
    private val pizzaAdapter = PizzaAdapter()
    private val binding get() = _binding!!

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

        pizzaAdapter.setData(
            listOf(
                PizzaModel(
                    getString(R.string.ham_and_mushrooms),
                    getString(R.string.description_ham_and_mushrooms),
                    image = ContextCompat.getDrawable(requireContext(), R.drawable.pizza_1)!!
                ),

                PizzaModel(
                    getString(R.string.tender_salmon),
                    getString(R.string.tender_salmon),
                    image = ContextCompat.getDrawable(requireContext(), R.drawable.pizza_2)!!
                ),

                PizzaModel(
                    getString(R.string.ham_and_mushrooms),
                    getString(R.string.description_ham_and_mushrooms),
                    image = ContextCompat.getDrawable(requireContext(), R.drawable.pizza_1)!!
                ),

                PizzaModel(
                    getString(R.string.tender_salmon),
                    getString(R.string.tender_salmon),
                    image = ContextCompat.getDrawable(requireContext(), R.drawable.pizza_2)!!
                ),

                PizzaModel(
                    getString(R.string.ham_and_mushrooms),
                    getString(R.string.description_ham_and_mushrooms),
                    image = ContextCompat.getDrawable(requireContext(), R.drawable.pizza_1)!!
                ),

                PizzaModel(
                    getString(R.string.tender_salmon),
                    getString(R.string.tender_salmon),
                    image = ContextCompat.getDrawable(requireContext(), R.drawable.pizza_2)!!
                ),

                PizzaModel(
                    getString(R.string.ham_and_mushrooms),
                    getString(R.string.description_ham_and_mushrooms),
                    image = ContextCompat.getDrawable(requireContext(), R.drawable.pizza_1)!!
                )
            )
        )

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