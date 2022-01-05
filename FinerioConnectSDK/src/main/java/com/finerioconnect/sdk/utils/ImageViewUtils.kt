package com.finerioconnect.sdk.utils

import android.content.res.ColorStateList
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.finerioconnect.sdk.R
import com.finerioconnect.sdk.extensions.findImageIdentifier
import java.net.MalformedURLException

object ImageViewUtils {

    fun setResource(
        imageString: String? = null,
        imageView: ImageView,
        imageColor: Int? = null,
        customImage: String? = null
    ) {
        if (imageString == null || imageString.isEmpty()) {
            setDefaultImage(imageView, imageColor, customImage)
        } else {
            if (isValidUrl(imageString)) {
                Glide.with(imageView.context).load(imageString).into(imageView)
            } else {
                val imageId = imageView.context.findImageIdentifier(imageString)
                if (imageId != 0) {
                    imageView.setImageResource(imageId)
                    setColorImage(imageView, imageColor)
                } else {
                    setDefaultImage(imageView, imageColor, customImage)
                }
            }
        }
    }

    private fun isValidUrl(imageString: String): Boolean = try {
        URLUtil.isValidUrl(imageString) && Patterns.WEB_URL.matcher(imageString).matches()
    } catch (ignored: MalformedURLException) {
        false
    }

    private fun setDefaultImage(imageView: ImageView, imageColor: Int?, customImage: String?) {
        if (customImage == null) {
            val defaultDrawable =
                ContextCompat.getDrawable(imageView.context, R.drawable.bg_circle_white)
            imageView.setImageDrawable(defaultDrawable)

            if (imageColor != null) {
                val filter: ColorFilter =
                    PorterDuffColorFilter(imageColor, PorterDuff.Mode.SRC_ATOP)
                if (defaultDrawable != null) {
                    defaultDrawable.colorFilter = filter
                }
            }
            imageView.layoutParams.width = 30
            imageView.layoutParams.height = 30
        } else {
            val image = imageView.context.findImageIdentifier(customImage)
            if (image != 0) imageView.setImageResource(image)

            val colorStateList =
                if (imageColor == null) null else ColorStateList.valueOf(imageColor)
            ImageViewCompat.setImageTintList(imageView, colorStateList)
        }
    }

    private fun setColorImage(imageView: ImageView, imageColor: Int?) {
        if (imageColor != null) {
            imageView.setColorFilter(imageColor, PorterDuff.Mode.SRC_ATOP)
        }
    }
}