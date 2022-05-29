package com.android.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.cryptoapp.R
import com.android.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.android.cryptoapp.databinding.ActivityCoinPriceBinding
import javax.inject.Inject


class CoinPriceActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as CryptoApp).component
    }

    private val binding by lazy {
        ActivityCoinPriceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val rvAdapter = CoinInfoAdapter(this)
        viewModel.coinInfoList.observe(this) {
            rvAdapter.submitList(it)
        }
        binding.recyclerView.adapter = rvAdapter
        binding.recyclerView.itemAnimator = null
        rvAdapter.onCoinClickListener = {
            if (isOnePaneMode()) launchDetailActivity(it.fromSymbol)
            else launchDetailFragment(it.fromSymbol)
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CoinInfoDetailActivity.newIntent(this@CoinPriceActivity, fromSymbol)
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CoinInfoDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }
}