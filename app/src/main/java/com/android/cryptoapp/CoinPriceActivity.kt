package com.android.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.cryptoapp.adapters.CoinInfoAdapter
import com.android.cryptoapp.databinding.ActivityCoinPriceBinding
import com.android.cryptoapp.pojo.CoinPriceInfo


class CoinPriceActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCoinPriceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val rvAdapter = CoinInfoAdapter(this)
        viewModel.priceList.observe(this) {
            rvAdapter.submitList(it)
        }
        binding.recyclerView.adapter = rvAdapter
        rvAdapter.onCoinClickListener = {
            Log.d("ON_CLICK", it.fromSymbol)
        }
    }
}