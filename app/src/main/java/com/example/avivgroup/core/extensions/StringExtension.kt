package com.example.avivgroup.core.extensions

import android.util.Patterns
import com.example.avivgroup.App
import com.example.avivgroup.R

/**
 * The StringExtension.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */


/**
 * Extension function to noNetworkErrorMessage
 * @author Dawar Malik.
 */
fun noNetworkErrorMessage() =
    App.getAppContext().getString(R.string.message_no_network_connected_str)

/**
 * Extension function to somethingWentWrong
 * @author Dawar Malik.
 */
fun somethingWentWrong() = App.getAppContext().getString(R.string.message_something_went_wrong_str)

/**
 * Extension function to Verify the URL
 * @author Dawar Malik.
 */
fun String.checkValidURL(): String? {
    if (Patterns.WEB_URL.matcher(this).matches())
        return this

    return null
}