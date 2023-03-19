package com.example.avivgroup.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.avivgroup.adapters.PhotosAdapter
import com.example.avivgroup.base.BaseFragment
import com.example.avivgroup.core.extensions.replaceFragment
import com.example.avivgroup.core.extensions.showToastMsg
import com.example.avivgroup.databinding.FragmentHomeBinding
import com.example.avivgroup.ui.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        photosAdapter = PhotosAdapter().also {
            it.onPhotoSelectionListener { photoModel ->
                mainActivity.savePhotoModel(photoModel)
                replaceFragment(DetailsFragment(), addToBackStack = true)
            }

            it.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            viewBinding.photosRecyclerView.adapter = it
        }

        initObservations()
    }

    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    progressDialog.show()
                }

                is ErrorState -> {
                    progressDialog.dismiss()
                    showToastMsg(state.message)
                }
            }
        }

        viewModel.propertiesListLiveData.observe(viewLifecycleOwner) { photos ->
            photosAdapter.setItems(photos)
        }
    }
}
