package com.android.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.android.cryptoapp.data.model.CoinPriceInfo

class CoinItemDiffCallback: DiffUtil.ItemCallback<CoinPriceInfo>() {
    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }
}