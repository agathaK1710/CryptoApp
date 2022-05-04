package com.android.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.cryptoapp.R
import com.android.cryptoapp.databinding.ItemCoinPriceInfoBinding
import com.android.cryptoapp.data.network.model.CoinInfoDto
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    ListAdapter<CoinInfoDto, CoinInfoAdapter.CoinViewHolder>(CoinItemDiffCallback()) {
    var onCoinClickListener: ((CoinInfoDto) -> Unit)? = null

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
            val lastUpdate = context.resources.getString(R.string.last_update)
            tvCost.text = item.price.toString()
            tvSymbols.text = String.format(symbolsTemplate, item.fromSymbol, item.toSymbol)
            tvTime.text = String.format(lastUpdate, item.getFormattedDate())
            Picasso.get().load(item.getFullImageUrl()).into(ivLogoCoin)
            itemView.setOnClickListener {
                onCoinClickListener?.invoke(item)
            }
        }
    }
}