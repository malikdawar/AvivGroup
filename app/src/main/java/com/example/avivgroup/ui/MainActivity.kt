package com.example.avivgroup.ui

import android.os.Bundle
import com.example.avivgroup.R
import com.example.avivgroup.base.BaseActivity
import com.example.avivgroup.core.extensions.replaceFragmentSafely
import com.example.avivgroup.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity.kt, Main activity class, launcher activity
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentSafely(HomeFragment())
    }
}
