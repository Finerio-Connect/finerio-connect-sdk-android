package com.finerioconnect.sdk.ui.components.segmentbutton

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.finerioconnect.sdk.R
import com.finerioconnect.sdk.databinding.PartialSegmentButtonsBinding
import com.finerioconnect.sdk.ui.components.segmentbutton.BackgroundType.*

class SegmentButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    private val mBinding =
        PartialSegmentButtonsBinding.inflate(LayoutInflater.from(context), this, true)

    private var mSbBackground: Int = ContextCompat.getColor(context, R.color.colorWhite)

    private var mSbBackgroundSelected: Int = ContextCompat.getColor(context, R.color.colorPrimary)

    private var mSbTextColor: Int = ContextCompat.getColor(context, R.color.colorPrimary)

    private var mSbTextColorSelected: Int = ContextCompat.getColor(context, R.color.colorWhite)

    private var mListener: OnClickSegmentButtonListener? = null

    private var mSegmentButtons = arrayListOf<SegmentButtonModel>()

    private var mButtons = ArrayList<Button>()

    private var mDefaultSelected = 0

    init {
        orientation = HORIZONTAL
        clearButtons()
    }

    fun setListener(listener: OnClickSegmentButtonListener) = apply {
        this.mListener = listener
    }

    fun addSegmentButtons(buttons: List<SegmentButtonModel>) = apply {
        this.mSegmentButtons.addAll(buttons)
    }

    fun addSegmentButton(button: SegmentButtonModel) = apply {
        this.mSegmentButtons.add(button)
    }

    fun setSBBackground(backgroundColor: Int) = apply {
        this.mSbBackground = backgroundColor
    }

    fun setSBBackgroundSelected(backgroundColorSelected: Int) = apply {
        this.mSbBackgroundSelected = backgroundColorSelected
    }

    fun setSBTextColor(textColor: Int) = apply {
        this.mSbTextColor = textColor
    }

    fun setSBTextColorSelected(textColorSelected: Int) = apply {
        this.mSbTextColorSelected = textColorSelected
    }

    fun setDefaultSelected(index: Int) = apply {
        this.mDefaultSelected = index
    }

    fun config() {
        this.background = createBackground(COMPLETE_BORDER, 5, false)
        mSegmentButtons.forEachIndexed { index, SegmentButtonModel ->
            val segmentButton = buildButton(index, SegmentButtonModel)
            mBinding.llContainer.addView(segmentButton)
            mButtons.add(segmentButton)
        }
    }

    fun changeSBTitle(index: Int, title: String) {
        if (index > mButtons.size - 1) {
            return
        }
        mSegmentButtons[index].title = title
        mButtons[index].text = title
    }

    fun getSBSelected(): SegmentButtonModel? = if (mSegmentButtons.isNullOrEmpty()) {
        null
    } else {
        mSegmentButtons[mDefaultSelected]
    }

    fun getSBIndex() = mDefaultSelected

    private fun buildButton(index: Int, segmentButton: SegmentButtonModel): Button {
        val numberButtons: Float = mSegmentButtons.size.toFloat()
        val buttonSize: Float = 1 / numberButtons
        val lpSegmentButton = LayoutParams(0, LayoutParams.MATCH_PARENT, buttonSize)

        val isSelected = index == mDefaultSelected

        val button = Button(context).apply {
            text = segmentButton.title
            layoutParams = lpSegmentButton
            gravity = Gravity.CENTER
            isAllCaps = false
            if (isSelected) {
                setTextColor(mSbTextColorSelected)
            } else {
                setTextColor(mSbTextColor)
            }
            background = createBackground(buttonBorderType(index), 2, isSelected)
        }
        button.setOnClickListener {
            if (mDefaultSelected != index) {
                mListener?.clickSegmentButton(index)
            }
            selectSegmentButton(index)
        }
        return button
    }

    private fun createBackground(type: BackgroundType, width: Int, isSelected: Boolean): GradientDrawable {
        val shape = GradientDrawable()
        when (type) {
            LEFT_BORDER -> shape.cornerRadii = SegmentButtonConstants.LEFT_CORNER_RADIUS
            RIGHT_BORDER -> shape.cornerRadii = SegmentButtonConstants.RIGHT_CORNER_RADIUS
            COMPLETE_BORDER -> shape.cornerRadius = SegmentButtonConstants.CORNER_RADIUS
            COMPLETE_BORDER_WITHOUT_RADIUS -> shape.cornerRadius = 0f
        }
        if (isSelected) {
            shape.setColor(mSbBackgroundSelected)
        } else {
            shape.setStroke(width, mSbBackgroundSelected)
            shape.setColor(mSbBackground)
        }
        return shape
    }

    private fun buttonBorderType(index: Int) =
        if (index == 0 && mSegmentButtons.size > 1) {
            LEFT_BORDER
        } else if (index == 0 && mSegmentButtons.size == 1) {
            COMPLETE_BORDER
        } else if (index == mSegmentButtons.size - 1) {
            RIGHT_BORDER
        } else {
            COMPLETE_BORDER_WITHOUT_RADIUS
        }

    fun selectSegmentButton(index: Int) {
        if (mDefaultSelected == index) {
            return
        }

        mDefaultSelected = index
        mButtons.forEachIndexed { indexButton, button ->
            val isSelected = mDefaultSelected == indexButton
            button.background = createBackground(buttonBorderType(indexButton), 2, isSelected)
            if (isSelected) {
                button.setTextColor(mSbTextColorSelected)
            } else {
                button.setTextColor(mSbTextColor)
            }
        }
    }

    fun clearButtons(){
        mSegmentButtons.clear()
        mButtons.clear()
        mBinding.llContainer.removeAllViews()
        mDefaultSelected = 0
    }

}