package com.android.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.cryptoapp.data.database.AppDatabase
import com.android.cryptoapp.data.database.mapper.CoinMapper
import com.android.cryptoapp.data.network.ApiFactory
import com.android.cryptoapp.domain.CoinInfo
import com.android.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinInfoRepositoryImpl(application: Application): CoinRepository {
    private val mapper = CoinMapper()
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
       return Transformations.map(coinInfoDao.getPriceList()){
           it.map {
               mapper.mapDbModelToEntity(it)
           }
       }
    }

    override fun getCoinInfo(fSym: String): LiveData<CoinInfo> {
      return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fSym)){
          mapper.mapDbModelToEntity(it)
      }
    }

    override suspend fun loadData() {
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
}