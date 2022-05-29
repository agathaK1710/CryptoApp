package com.android.cryptoapp.di

import android.app.Application
import com.android.cryptoapp.presentation.CoinInfoDetailFragment
import com.android.cryptoapp.presentation.CoinPriceActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: CoinPriceActivity)

    fun inject(fragment: CoinInfoDetailFragment)

    @Component.Factory
    interface AppComponentFactory{
        fun create(
            @BindsInstance
            application: Application
        ): AppComponent
    }
}