package com.android.cryptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.android.cryptoapp.data.database.AppDatabase
import com.android.cryptoapp.data.database.CoinPriceInfoDao
import com.android.cryptoapp.data.database.mapper.CoinMapper
import com.android.cryptoapp.data.network.ApiFactory
import com.android.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay

class RefreshDetailWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinPriceInfoDao,
    private val apiService: ApiService
) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fsyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    companion object {
        val NAME = "RefreshDetailWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDetailWorker>().build()
        }
    }
}