package com.android.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.cryptoapp.data.repository.CoinInfoRepositoryImpl
import com.android.cryptoapp.domain.GetCoinInfoListUseCase
import com.android.cryptoapp.domain.GetCoinInfoUseCase
import com.android.cryptoapp.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CoinInfoRepositoryImpl(application)

    val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}