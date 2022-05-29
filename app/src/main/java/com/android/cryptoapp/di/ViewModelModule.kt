package com.android.cryptoapp.di

import androidx.lifecycle.ViewModel
import com.android.cryptoapp.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindViewModel(coinViewModel: CoinViewModel): ViewModel
}