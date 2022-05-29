package com.android.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.android.cryptoapp.data.database.AppDatabase
import com.android.cryptoapp.data.database.mapper.CoinMapper
import com.android.cryptoapp.data.network.ApiFactory
import com.android.cryptoapp.data.workers.RefreshDetailWorkerFactory
import com.android.cryptoapp.di.DaggerAppComponent

class CryptoApp: Application(), Configuration.Provider {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDetailWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }
}