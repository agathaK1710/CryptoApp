package com.android.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.android.cryptoapp.data.database.AppDatabase
import com.android.cryptoapp.data.database.CoinPriceInfoDao
import com.android.cryptoapp.data.database.mapper.CoinMapper
import com.android.cryptoapp.data.network.ApiFactory
import com.android.cryptoapp.data.workers.RefreshDetailWorker
import com.android.cryptoapp.domain.CoinInfo
import com.android.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinInfoRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinPriceInfoDao
) : CoinRepository {


    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fSym: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fSym)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        var workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDetailWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDetailWorker.makeRequest()
        )
    }
}