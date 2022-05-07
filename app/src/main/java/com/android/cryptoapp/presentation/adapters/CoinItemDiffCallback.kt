package com.android.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.android.cryptoapp.data.network.model.CoinInfoDto
import com.android.cryptoapp.domain.CoinInfo

class CoinItemDiffCallback: DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}