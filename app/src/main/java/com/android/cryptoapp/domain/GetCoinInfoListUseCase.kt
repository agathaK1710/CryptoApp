package com.android.cryptoapp.domain

class GetCoinInfoListUseCase(private val repository: CoinRepository){
    operator fun invoke() = repository.getCoinInfoList()
}