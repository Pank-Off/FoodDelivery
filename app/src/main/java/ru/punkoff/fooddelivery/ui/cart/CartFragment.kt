package ru.punkoff.fooddelivery.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.punkoff.fooddelivery.MainActivity
import ru.punkoff.fooddelivery.R
import ru.punkoff.fooddelivery.databinding.FragmentCartBinding
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import ru.punkoff.fooddelivery.ui.menu.ui.adapter.MenuAdapter
import ru.punkoff.fooddelivery.utils.isOnline

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CartViewModel by viewModels()
    private val adapter = MenuAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestData()

        with(binding) {
            viewModel.observeLiveData().observe(viewLifecycleOwner) {
                when (it) {
                    MenuViewState.EMPTY -> {
                        withOrdersLayout.root.visibility = View.GONE
                        emptyLayout.root.visibility = View.VISIBLE
                    }
                    MenuViewState.Loading -> {
                        adapter.loadingData()
                    }
                    is MenuViewState.Success -> {
                        withOrdersLayout.root.visibility = View.VISIBLE
                        emptyLayout.root.visibility = View.GONE

                        with(withOrdersLayout) {
                            ordersRecycler.layoutManager =
                                LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            adapter.setData(it.items)
                            val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                            ordersRecycler.addItemDecoration(dividerItemDecoration)
                            ordersRecycler.adapter = adapter
                        }

                    }
                }
            }
            emptyLayout.navigateToMenuBtn.setOnClickListener {
                (requireActivity() as MainActivity).navigateToMainMenu()
            }

            withOrdersLayout.deliverBtn.setOnClickListener {
                if(isOnline(requireContext())) {
                    viewModel.clearOrders()
                    Snackbar.make(view, getString(R.string.thanks_for_order), Snackbar.LENGTH_SHORT)
                        .setAction(
                            getString(R.string.ok)
                        ) { }.show()
                }else{
                    Snackbar.make(
                        view,
                        getString(R.string.no_internet_msg),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(getString(R.string.ok)) {}.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}