package com.android.cryptoapp.di

import android.app.Application
import com.android.cryptoapp.data.database.AppDatabase
import com.android.cryptoapp.data.database.CoinPriceInfoDao
import com.android.cryptoapp.data.network.ApiFactory
import com.android.cryptoapp.data.network.ApiService
import com.android.cryptoapp.data.repository.CoinInfoRepositoryImpl
import com.android.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindCoinInfoRepository(impl: CoinInfoRepositoryImpl): CoinRepository

    companion object{
        @ApplicationScope
        @Provides
        fun provideCoinInfoDao(application: Application): CoinPriceInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}