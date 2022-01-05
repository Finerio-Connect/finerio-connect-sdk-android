package com.finerioconnect.sdk.ui.adapters.transactions.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.finerioconnect.sdk.core.FinerioConnectSDK
import com.finerioconnect.sdk.databinding.ItemHeaderDateBinding
import com.finerioconnect.sdk.extensions.formatAmount
import com.finerioconnect.sdk.extensions.safe
import com.finerioconnect.sdk.ui.adapters.transactions.models.FCTransactionHeader
import com.finerioconnect.sdk.ui.models.TransactionListPalette

class FCTransactionHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mBinding = ItemHeaderDateBinding.bind(itemView)

    fun bind(header: FCTransactionHeader?, transactionListPalette: TransactionListPalette? = null) = with(mBinding) {
        tvDateTitle.text = header?.dateTitle.safe()
        header?.amount?.let {
            tvAmount.text = it.formatAmount()
        }
        transactionListPalette?.headerBackground?.let {
            llHeader.setBackgroundColor(it)
        }
        transactionListPalette?.headerTextColor?.let {
            tvDateTitle.setTextColor(it)
            tvAmount.setTextColor(it)
        }
        FinerioConnectSDK.shared.fonts.family?.let {
            tvDateTitle.typeface = it
            tvAmount.typeface = it
        }
    }

}