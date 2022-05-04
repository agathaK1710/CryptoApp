package com.android.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.android.cryptoapp.data.network.model.CoinInfoDto

class CoinItemDiffCallback: DiffUtil.ItemCallback<CoinInfoDto>() {
    override fun areItemsTheSame(oldItem: CoinInfoDto, newItem: CoinInfoDto): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoDto, newItem: CoinInfoDto): Boolean {
        return oldItem == newItem
    }
}