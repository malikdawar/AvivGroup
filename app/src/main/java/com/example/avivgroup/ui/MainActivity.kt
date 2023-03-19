package com.example.avivgroup.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.example.avivgroup.R
import com.example.avivgroup.base.BaseActivity
import com.example.avivgroup.core.extensions.replaceFragmentSafely
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity.kt, Main activity class, launcher activity
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentSafely(HomeFragment())
    }

    fun savePhotoModel(photo: PropertyModel) {
        viewModel.savePhotoModel(photo)
    }
}
