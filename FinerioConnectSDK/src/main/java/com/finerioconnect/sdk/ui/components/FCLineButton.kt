package com.finerioconnect.sdk.ui.components

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.finerioconnect.sdk.R
import com.finerioconnect.sdk.core.FinerioConnectSDK

class FCLineButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private val mView = LayoutInflater.from(context).inflate(R.layout.fc_line_button, this, true)

    private var mIvIcon: ImageView? = null
    private var mTvTitle: TextView? = null

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.FCButtonLine, 0, 0).apply {
            try {
                init(this)
            } finally {
                recycle()
            }
        }
    }

    private fun init(typedArray: TypedArray){
        mIvIcon = mView.findViewById(R.id.ivIcon)
        mTvTitle = mView.findViewById(R.id.tvTitle)

        val icon = typedArray.getResourceId(R.styleable.FCButtonLine_fcIcon, 0)
        val iconTint = typedArray.getResourceId(R.styleable.FCButtonLine_fcIconTint, 0)
        configIcon(mIvIcon!!, icon, iconTint)

        val title = typedArray.getResourceId(R.styleable.FCButtonLine_fcTitle, 0)
        val titleColor = typedArray.getResourceId(R.styleable.FCButtonLine_fcTitleColor, 0)
        configText(mTvTitle!!, title, titleColor)
    }

    private fun configIcon(imageView: ImageView, icon: Int, tint: Int) {
        if (icon != 0) {
            imageView.setImageResource(icon)
        }
        if (tint != 0) {
            val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(context, tint))
            ImageViewCompat.setImageTintList(imageView, colorStateList)
        }
    }

    private fun configText(tvTitle: TextView, title: Int, titleColor: Int) {
        if (title != 0) {
            tvTitle.text = context.getString(title)
        }
        if (titleColor != 0) {
            tvTitle.setTextColor(ContextCompat.getColor(context, titleColor))
        }
        FinerioConnectSDK.shared.fonts.family?.let {
            tvTitle.typeface = it
        }
    }

    /**
     * Method to set icon
     *
     * e.g. R.drawable.resource_name
     */
    fun setIcon(icon: Int) = with(mIvIcon) {
        this?.setImageResource(icon)
    }

    /**
     * Method to set tint color
     *
     * e.g. ContextCompat.getColor(context, tint)
     */
    fun setTintColor(tint: Int) = with(mIvIcon) {
        val colorStateList = ColorStateList.valueOf(tint)
        this?.let { ImageViewCompat.setImageTintList(it, colorStateList) }
    }

    fun setTitle(title: String) = with(mTvTitle) {
        this?.text = title
    }

    fun setTitle(title: Int) = with(mTvTitle) {
        this?.text = context.getString(title)
    }

    /**
     * Method to set title color
     *
     * e.g. ContextCompat.getColor(context, titleColor)
     */
    fun setTitleColor(titleColor: Int) = with(mTvTitle) {
        this?.setTextColor(titleColor)
    }

}