package com.finerioconnect.sdk.ui.adapters.transactions.viewholders

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.setPadding
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finerioconnect.sdk.R
import com.finerioconnect.sdk.core.FinerioConnectSDK
import com.finerioconnect.sdk.databinding.ItemTransactionsBinding
import com.finerioconnect.sdk.extensions.*
import com.finerioconnect.sdk.ui.adapters.transactions.FCTransactionAdapter
import com.finerioconnect.sdk.ui.adapters.transactions.models.FCTransactionBody
import com.finerioconnect.sdk.ui.adapters.transactions.models.FCTransactionCategory
import com.finerioconnect.sdk.utils.ImageViewUtils
import com.finerioconnect.sdk.utils.Utils

class FCTransactionBodyViewHolder<T>(
    itemView: View, val listener: FCTransactionAdapter.OnClickTransactionListener<T>
) : RecyclerView.ViewHolder(itemView) {

    private val mBinding = ItemTransactionsBinding.bind(itemView)

    fun bind(body: FCTransactionBody<T>?, defMainImage: String?) = with(mBinding) {
        if (body == null) return@with

        tvTransactionName.text = body.name.safe()
        llTransaction.setOnClickListener { _ ->
            body.data?.let { listener.onClick(it) }
        }
        val backgroundColor = if(body.duplicated) R.color.colorTransactionDuplicated else R.color.colorWhite
        llTransaction.background = ContextCompat.getDrawable(llTransaction.context, backgroundColor)

        configImage(body, defMainImage)

        configAmount(body.amount ?: 0.0, body.configAmountColor)
        if (body.category == null) {
            rlTransactionTypeContainer.visibility = View.GONE
        } else {
            rlTransactionTypeContainer.visibility = View.VISIBLE
            configType(body.category!!)
        }
        configTypeface()
    }

    private fun configImage(body: FCTransactionBody<T>, defMainImage: String?) = with(mBinding.ivLogo) {
        if(defMainImage == null){
            if (body.image.isUrl()) {
                Glide.with(context).load(body.image).into(this)
            } else {
                val image = context.findImageIdentifier(body.image)
                if(image != 0) setImageResource(image)
                val imgPadding = if(body.image == "debit_manual_account") 6f else 10f
                setPadding(context.dpToPx(imgPadding).toInt())
            }
            val colorStateList = if(body.tintImage == null) null else ColorStateList.valueOf(body.tintImage!!)
            ImageViewCompat.setImageTintList(this, colorStateList)
        } else {
            setPadding(context.dpToPx(13f).toInt())
            ImageViewUtils.setResource(body.image, this, body.tintImage, defMainImage)
        }
    }

    private fun configType(category: FCTransactionCategory) = with(mBinding) {
        tvTransactionType.text = category.name

        category.backgroundColor?.let {
            ViewCompat.setBackground(rlTransactionTypeContainer, Utils.roundedBackground(12f, Color.parseColor(it)))
        }
        category.textColor?.let {
            tvTransactionType.setTextColor(Color.parseColor(it))
        }
        category.image?.let {
            if(category.image.name == ""){
                ivTransactionType.visibility = View.GONE
            } else {
                ImageViewUtils.setResource(
                    category.image.name,
                    ivTransactionType,
                    category.image.tintColor
                )
            }
        }
    }

    private fun configAmount(amount: Double, configAmountColor: Boolean) = with(mBinding.tvAmount) {
        text = amount.formatAmount()
        if(configAmountColor){
            if (amount > 0) {
                setTextColor(ContextCompat.getColor(context, R.color.colorBudgetGreen))
            } else {
                setTextColor(ContextCompat.getColor(context, R.color.colorBudgetRed))
            }
        } else {
            setTextColor(ContextCompat.getColor(context, R.color.colorDarkGray))
        }
    }

    private fun configTypeface() = with(mBinding) {
        FinerioConnectSDK.shared.fonts.family?.let {
            tvTransactionName.typeface = it
            tvTransactionType.typeface = it
            tvAmount.typeface = it
        }
    }

}