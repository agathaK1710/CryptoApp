package com.android.cryptoapp.di

import android.app.Application
import com.android.cryptoapp.presentation.CoinInfoDetailFragment
import com.android.cryptoapp.presentation.CoinPriceActivity
import com.android.cryptoapp.presentation.CryptoApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface AppComponent {

    fun inject(activity: CoinPriceActivity)

    fun inject(fragment: CoinInfoDetailFragment)

    fun inject(application: CryptoApp)

    @Component.Factory
    interface AppComponentFactory{
        fun create(
            @BindsInstance
            application: Application
        ): AppComponent
    }
}