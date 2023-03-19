package com.example.avivgroup.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.avivgroup.core.utils.Const.PLACE_HOLDER_IMAGE
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

/**
 * A convenient function to load images from internet on ImageView.
 * @see Glide The library used which loads memory cached images.
 * @author Malik Dawar, malikdawar@hotmail.com
 */
private val shimmer =
    Shimmer.AlphaHighlightBuilder()
        .setDuration(1800)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

fun ImageView.load(imageUrl: String?) {
    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
    Glide.with(this).load(imageUrl ?: PLACE_HOLDER_IMAGE).placeholder(shimmerDrawable).into(this)
}
