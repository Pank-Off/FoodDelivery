package ru.punkoff.fooddelivery.ui.menu.ui.tablayout.fragments

import ru.punkoff.fooddelivery.databinding.FragmentComboBinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.punkoff.fooddelivery.databinding.FragmentPizzaBinding

class ComboFragment : Fragment() {

    private var _binding: FragmentComboBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComboBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}