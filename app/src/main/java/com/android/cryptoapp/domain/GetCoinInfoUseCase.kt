package com.android.cryptoapp.domain

class GetCoinInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fSym: String) = repository.getCoinInfo(fSym)
}