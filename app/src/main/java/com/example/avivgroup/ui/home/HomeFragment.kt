package com.example.avivgroup.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.avivgroup.R
import com.example.avivgroup.adapters.PropertyAdapter
import com.example.avivgroup.base.BaseFragment
import com.example.avivgroup.core.extensions.replaceFragment
import com.example.avivgroup.core.extensions.showToastMsg
import com.example.avivgroup.databinding.FragmentHomeBinding
import com.example.avivgroup.ui.MainViewModel
import com.example.avivgroup.ui.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var propertyAdapter: PropertyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        propertyAdapter = PropertyAdapter().also {
            it.onPropertySelectionListener { property ->
                sharedViewModel.savePropertyModel(property)
                replaceFragment(DetailsFragment(), addToBackStack = true)
            }

            it.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            viewBinding.propertiesRecyclerView.adapter = it
        }

        initObservations()
    }

    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    progressDialog.show()
                }

                is ContentState -> {
                    progressDialog.dismiss()
                }

                is ErrorState -> {
                    progressDialog.dismiss()
                    showToastMsg(state.message)
                }
            }
        }

        viewModel.propertiesListLiveData.observe(viewLifecycleOwner) { properties ->
            propertyAdapter.setItems(properties)
        }
    }
}
