package com.android.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.android.cryptoapp.data.database.CoinPriceInfoDao
import com.android.cryptoapp.data.database.mapper.CoinMapper
import com.android.cryptoapp.data.network.ApiService

class RefreshDetailWorkerFactory(
    private val coinPriceInfoDao: CoinPriceInfoDao,
    private val apiService: ApiService,
    private val coinMapper: CoinMapper
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return RefreshDetailWorker(
            appContext,
            workerParameters,
            coinMapper,
            coinPriceInfoDao,
            apiService
        )
    }
}