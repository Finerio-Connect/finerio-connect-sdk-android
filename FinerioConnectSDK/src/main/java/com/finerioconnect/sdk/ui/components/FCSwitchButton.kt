package com.finerioconnect.sdk.ui.components

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.finerioconnect.sdk.R
import com.finerioconnect.sdk.core.FinerioConnectSDK
import com.finerioconnect.sdk.extensions.dpToPx
import com.finerioconnect.sdk.ui.models.SwitchButtonPalette

class FCSwitchButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private val mView = LayoutInflater.from(context).inflate(R.layout.fc_switch_button, this, true)

    private val mRlSwitchButton: RelativeLayout

    private val mTvTitle: TextView

    private val mShape = GradientDrawable()

    private var mStrokeColor: Int = 0

    private var mBgColor: Int

    private var mBgColorActivated: Int

    private var mTitle: String = ""

    private var mTitleColor: Int

    private var mTitleColorActivated: Int

    private var isSwitchActivate: Boolean = false

    private var mListener: OnFCSwitchButtonClickListener? = null

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.FCSwitchButton, 0, 0).apply {
            try {
                mRlSwitchButton = mView.findViewById(R.id.rlSwitchButton)
                mTvTitle = mView.findViewById(R.id.tvTitle)

                val strokeColor =
                    getResourceId(R.styleable.FCSwitchButton_fcSBStrokeColor, android.R.color.black)
                val bgColor =
                    getResourceId(
                        R.styleable.FCSwitchButton_fcSBBackgroundColor,
                        android.R.color.white
                    )
                val bgColorActivated =
                    getResourceId(
                        R.styleable.FCSwitchButton_fcSBBackgroundColorActivated,
                        android.R.color.black
                    )
                val titleRes = getResourceId(R.styleable.FCSwitchButton_fcSBTitle, 0)
                val titleColor =
                    getResourceId(R.styleable.FCSwitchButton_fcSBTitleColor, android.R.color.black)
                val titleColorActivated =
                    getResourceId(
                        R.styleable.FCSwitchButton_fcSBTitleColorActivated,
                        android.R.color.white
                    )
                val height = getInt(R.styleable.FCSwitchButton_fcSBHeight, 35)

                isSwitchActivate = getBoolean(R.styleable.FCSwitchButton_fcIsSBActivate, false)
                mStrokeColor = ContextCompat.getColor(context, strokeColor)
                mBgColor = ContextCompat.getColor(context, bgColor)
                mBgColorActivated = ContextCompat.getColor(context, bgColorActivated)
                mTitleColor = ContextCompat.getColor(context, titleColor)
                mTitleColorActivated = ContextCompat.getColor(context, titleColorActivated)

                if (titleRes != 0) mTitle = context.getString(titleRes)

                configTitle(mTitle, mTitleColor)
                setButtonActivated(isSwitchActivate)

                mRlSwitchButton.setOnClickListener {
                    isSwitchActivate = !isSwitchActivate
                    configButtonState()
                    mListener?.onClick(isSwitchActivate)
                }
                mRlSwitchButton.layoutParams.height = context.dpToPx(height.toFloat()).toInt()
            } finally {
                recycle()
            }
        }
    }

    private fun configBackground(strokeColor: Int, bgColor: Int) {
        mShape.apply {
            cornerRadius = 12f
            setStroke(context.dpToPx(1.5f).toInt(), strokeColor)
            setColor(bgColor)
            ViewCompat.setBackground(mRlSwitchButton, this)
        }
    }

    private fun configTitle(title: String, titleColor: Int) {
        mTvTitle.text = title
        if (titleColor != 0) {
            mTvTitle.setTextColor(titleColor)
        }
        FinerioConnectSDK.shared.fonts.family?.let {
            mTvTitle.typeface = it
        }
    }

    fun setTitle(title: Int) {
        mTitle = context.getString(title)
        mView.findViewById<TextView>(R.id.tvTitle).text = mTitle
    }

    fun setTitle(title: String) {
        mTitle = title
        mView.findViewById<TextView>(R.id.tvTitle).text = title
    }

    fun isButtonActivated(): Boolean = isSwitchActivate

    fun setButtonActivated(isActivated: Boolean) {
        this.isSwitchActivate = isActivated
        configButtonState()
    }

    private fun configButtonState() {
        if (this.isSwitchActivate) {
            configBackground(mStrokeColor, mBgColorActivated)
            configTitle(mTitle, mTitleColorActivated)
        } else {
            configBackground(mStrokeColor, mBgColor)
            configTitle(mTitle, mTitleColor)
        }
    }

    fun setListener(listener: OnFCSwitchButtonClickListener) {
        this.mListener = listener
    }

    fun setPalette(switchButton: SwitchButtonPalette?){
        switchButton?.let { colors ->
            colors.strokeColor?.let { mStrokeColor = it }
            colors.bgColor?.let { mBgColor = it }
            colors.bgColorActivated?.let { mBgColorActivated = it }
            colors.titleColor?.let { mTitleColor = it }
            colors.titleColorActivated?.let { mTitleColorActivated = it }
            configButtonState()
        }
    }

}

interface OnFCSwitchButtonClickListener {

    fun onClick(isActivated: Boolean)

}
