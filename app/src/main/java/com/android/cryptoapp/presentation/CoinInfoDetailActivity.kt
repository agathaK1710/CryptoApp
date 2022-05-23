package com.android.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.cryptoapp.R
import com.android.cryptoapp.databinding.ActivityCoinInfoDetailBinding
import com.squareup.picasso.Picasso

class CoinInfoDetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinInfoDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, CoinInfoDetailFragment.newInstance(fromSymbol))
                .commit()
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fsym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinInfoDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}