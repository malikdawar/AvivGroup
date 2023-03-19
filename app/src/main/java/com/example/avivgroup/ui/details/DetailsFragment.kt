package com.example.avivgroup.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.avivgroup.base.BaseFragment
import com.example.avivgroup.core.extensions.backPress
import com.example.avivgroup.core.utils.load
import com.example.avivgroup.databinding.FragmentPhotoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var viewBinding: FragmentPhotoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainActivity.viewModel.propertyModelLiveData.value?.let {
            viewModel.initPhotoModelFromSharedViewMOdel(it)
        } ?: mainActivity.backPress()

        initObservations()
    }

    private fun initObservations() {
        viewModel.propertyModelLiveData.observe(viewLifecycleOwner) {
            viewBinding.photoView.load(it.url)
        }
    }
}
