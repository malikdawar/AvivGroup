package com.example.avivgroup.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.avivgroup.R
import com.example.avivgroup.base.BaseFragment
import com.example.avivgroup.core.extensions.backPress
import com.example.avivgroup.core.extensions.showToastMsg
import com.example.avivgroup.core.utils.load
import com.example.avivgroup.databinding.FragmentPropertyDetailsBinding
import com.example.avivgroup.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.fragment_property_details) {

    private val viewModel: DetailsViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var viewBinding: FragmentPropertyDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentPropertyDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.propertyIdLiveData.value?.let {
            viewModel.fetchProperty(it)
        } ?: getRootActivity().backPress()

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

        viewModel.propertyModelLiveData.observe(viewLifecycleOwner) {
            viewBinding.photoView.load(it.url)
        }
    }
}
