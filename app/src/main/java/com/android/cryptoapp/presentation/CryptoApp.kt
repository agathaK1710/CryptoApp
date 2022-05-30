package com.android.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.android.cryptoapp.data.database.AppDatabase
import com.android.cryptoapp.data.database.mapper.CoinMapper
import com.android.cryptoapp.data.network.ApiFactory
import com.android.cryptoapp.data.workers.RefreshDetailWorkerFactory
import com.android.cryptoapp.di.DaggerAppComponent
import javax.inject.Inject

class CryptoApp: Application(), Configuration.Provider {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    @Inject
    lateinit var workerFactory: RefreshDetailWorkerFactory

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                workerFactory
            )
            .build()
    }
}