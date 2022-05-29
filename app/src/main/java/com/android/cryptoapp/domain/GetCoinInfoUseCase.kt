package com.android.cryptoapp.domain

import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(fSym: String) = repository.getCoinInfo(fSym)
}