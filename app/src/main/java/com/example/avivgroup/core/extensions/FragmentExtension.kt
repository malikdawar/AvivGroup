package com.example.avivgroup.core.extensions

import android.os.Bundle
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.example.avivgroup.R
import com.example.avivgroup.core.utils.FragmentViewBindingDelegate

/**
 * The FragmentExtension.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

/**
 * Extension function to replace Fragment Safely from AppCompatActivity
 * @param fragment destination Fragment
 * @param tag as String default value is fragment.javaClass.name
 * @param containerViewId as frame layout id default value is R.id.container
 * @param enterAnimation as starting animation
 * @param exitAnimation ending animation
 * @param popEnterAnimation if any
 * @param popExitAnimation if any
 * @author Dawar Malik.
 */
fun AppCompatActivity.replaceFragmentSafely(
    fragment: Fragment,
    tag: String = fragment.javaClass.name,
    allowStateLoss: Boolean = true,
    addToBackStack: Boolean = false,
    @IdRes containerViewId: Int = R.id.container,
    @AnimRes enterAnimation: Int = 0,
    @AnimRes exitAnimation: Int = 0,
    @AnimRes popEnterAnimation: Int = 0,
    @AnimRes popExitAnimation: Int = 0,
) {
    val ft = supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        .replace(containerViewId, fragment, tag)

    if (addToBackStack) {
        ft.addToBackStack(null)
    }

    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}

/**
 * Extension function to replace Fragment Safely from a Fragment
 * @param fragment destination Fragment
 * @param addToBackStack as Boolean default value is true
 * @param bundle as Bundle could be null
 * @author Dawar Malik.
 */
fun Fragment.replaceFragment(
    fragment: Fragment,
    addToBackStack: Boolean = true,
    bundle: Bundle? = null,
    shouldReplace: Boolean = true,
) {
    val transaction =
        this.parentFragmentManager.beginTransaction()
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    if (shouldReplace) {
        transaction.replace(R.id.container, fragment, fragment.javaClass.name)
    } else {
        transaction.add(R.id.container, fragment, fragment.javaClass.name)
    }

    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    fragment.arguments = bundle
    transaction.commit()
}

/**
 * Extension function to pop back stack
 * @return void
 * * @author Dawar Malik.
 */
fun AppCompatActivity.backPress() {
    val fragmentManager: FragmentManager = this.supportFragmentManager
    if (fragmentManager.backStackEntryCount >= 1) {
        fragmentManager.popBackStack()
    } else {
        this.finish()
    }
}


/**
 * Extension function to for the viewBinding in the fragmnet
 * @return Binding fun
 * * @author Dawar Malik.
 */
fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

