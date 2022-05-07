package com.android.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.cryptoapp.data.network.ApiFactory
import com.android.cryptoapp.data.database.AppDatabase
import com.android.cryptoapp.data.network.model.CoinInfoDto
import com.android.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.android.cryptoapp.data.repository.CoinInfoRepositoryImpl
import com.android.cryptoapp.domain.GetCoinInfoListUseCase
import com.android.cryptoapp.domain.GetCoinInfoUseCase
import com.android.cryptoapp.domain.LoadDataUseCase
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CoinInfoRepositoryImpl(application)

    val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}