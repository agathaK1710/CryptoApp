package com.android.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.android.cryptoapp.databinding.ActivityCoinPriceBinding


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
        viewModel.coinInfoList.observe(this) {
            rvAdapter.submitList(it)
        }
        binding.recyclerView.adapter = rvAdapter

        rvAdapter.onCoinClickListener = {
           val intent = CoinInfoDetailActivity.newIntent(this@CoinPriceActivity, it.fromSymbol)
            startActivity(intent)
        }
    }
}