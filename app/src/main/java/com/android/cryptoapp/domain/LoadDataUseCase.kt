package com.android.cryptoapp.domain

class LoadDataUseCase(private val repository: CoinRepository) {
   operator fun  invoke() = repository.loadData()
}