package com.android.cryptoapp.adapters

import androidx.recyclerview.widget.DiffUtil
import com.android.cryptoapp.pojo.CoinPriceInfo

class CoinItemDiffCallback: DiffUtil.ItemCallback<CoinPriceInfo>() {
    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }
}