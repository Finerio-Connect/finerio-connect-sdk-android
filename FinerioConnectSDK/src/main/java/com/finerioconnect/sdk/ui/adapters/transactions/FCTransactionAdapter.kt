package com.finerioconnect.sdk.ui.adapters.transactions

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finerioconnect.sdk.R
import com.finerioconnect.sdk.extensions.inflate
import com.finerioconnect.sdk.ui.adapters.transactions.models.FCTransactionBody
import com.finerioconnect.sdk.ui.adapters.transactions.models.FCTransactionHeader
import com.finerioconnect.sdk.ui.adapters.transactions.models.FCTransactionRow
import com.finerioconnect.sdk.ui.adapters.transactions.viewholders.FCTransactionBodyViewHolder
import com.finerioconnect.sdk.ui.adapters.transactions.viewholders.FCTransactionFooterViewHolder
import com.finerioconnect.sdk.ui.adapters.transactions.viewholders.FCTransactionHeaderViewHolder
import com.finerioconnect.sdk.ui.models.TransactionListPalette

class FCTransactionAdapter<T>(
    private val items: List<FCTransactionRow>,
    private val listener: OnClickTransactionListener<T>,
    private val transactionListPalette: TransactionListPalette? = null,
    private val defMainImage: String? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_VIEW_TYPE_HEADER = 0
        const val ITEM_VIEW_TYPE_ITEM = 1
        const val ITEM_VIEW_TYPE_FOOTER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> FCTransactionHeaderViewHolder(
                parent.inflate(R.layout.item_header_date, false)
            )
            ITEM_VIEW_TYPE_ITEM -> FCTransactionBodyViewHolder(
                parent.inflate(R.layout.item_transactions, false), listener
            )
            else -> FCTransactionFooterViewHolder(parent.inflate(R.layout.item_footer, false))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is FCTransactionHeaderViewHolder -> {
                holder.bind(item as FCTransactionHeader, transactionListPalette)
            }
            is FCTransactionFooterViewHolder -> {
                // Empty
            }
            else -> {
                (holder as FCTransactionBodyViewHolder<T>).bind(
                    item as FCTransactionBody<T>,
                    defMainImage
                )
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].viewType()

    interface OnClickTransactionListener<T> {

        fun onClick(data: T)

    }

}