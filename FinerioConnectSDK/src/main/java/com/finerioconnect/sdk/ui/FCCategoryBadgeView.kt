package com.finerioconnect.sdk.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.finerioconnect.sdk.databinding.PartialCategoryBadgeViewBinding
import com.finerioconnect.sdk.extensions.findImageIdentifier
import com.finerioconnect.sdk.extensions.isUrl
import com.finerioconnect.sdk.extensions.safe
import com.finerioconnect.sdk.models.FCCategory
import com.finerioconnect.sdk.models.FCImage

class FCCategoryBadgeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private val mBinding = PartialCategoryBadgeViewBinding
        .inflate(LayoutInflater.from(context), this, true)

    private var mCategory: FCCategory? = null

    fun showLoading(loadingText: String) = with(mBinding) {
        ivImage.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        tvCategory.text = loadingText
    }

    fun restoreCategory(){
        if(mCategory == null) return

        setCategory(mCategory!!)
    }

    fun setCategory(category: FCCategory) = with(mBinding) {
        mCategory = category

        if(category.image?.name?.safe() == ""){
            ivImage.visibility = View.GONE
        } else {
            ivImage.visibility = View.VISIBLE
            configImage(category.image)
        }
        progressBar.visibility = View.GONE
        tvCategory.text = category.name

        category.backgroundColor.let {
            val shape = GradientDrawable()
            shape.cornerRadius = 12f
            shape.setColor(Color.parseColor(it))
            ViewCompat.setBackground(rlCategoryBadgeContainer, shape)
        }
        category.textColor.let {
            tvCategory.setTextColor(Color.parseColor(it))
        }
    }

    private fun configImage(image: FCImage?) = with(mBinding.ivImage) {
        val categoryImage = image?.name.safe()
        if (categoryImage.isUrl()) {
            Glide.with(context).load(categoryImage).into(this)
        } else {
            val resourceImage = context.findImageIdentifier(categoryImage)
            if(resourceImage == 0){
                setImageResource(context.findImageIdentifier("user_category"))
            } else {
                setImageResource(resourceImage)
            }

            val tint = image?.tintColor
            if (tint == null) {
                ImageViewCompat.setImageTintList(this, null)
            } else {
                ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(tint))
            }
        }
    }

    fun getCategory() = mCategory

}