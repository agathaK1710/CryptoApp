package com.android.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.cryptoapp.R
import com.android.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.android.cryptoapp.databinding.ItemCoinPriceInfoBinding
import com.android.cryptoapp.domain.CoinInfo
import com.android.cryptoapp.utils.convertTimeStampToTime
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    ListAdapter<CoinInfo, CoinInfoAdapter.CoinViewHolder>(CoinItemDiffCallback()) {
    var onCoinClickListener: ((CoinInfo) -> Unit)? = null

    inner class CoinViewHolder(itemView: ItemCoinPriceInfoBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val tvCost = itemView.tvCost
        val tvSymbols = itemView.tvSymbols
        val tvTime = itemView.tvTime
        val ivLogoCoin = itemView.ivLogoCoin
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view =
            ItemCoinPriceInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update)
            tvCost.text = item.price.toString()
            tvSymbols.text = String.format(symbolsTemplate, item.fromSymbol, item.toSymbol)
            tvTime.text = String.format(lastUpdateTemplate, convertTimeStampToTime(item.lastUpdate))
            Picasso.get().load(BASE_IMAGE_URL + item.imageUrl).into(ivLogoCoin)
            itemView.setOnClickListener {
                onCoinClickListener?.invoke(item)
            }
        }
    }
}