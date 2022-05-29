package com.android.cryptoapp.presentation

import android.app.Application
import com.android.cryptoapp.di.DaggerAppComponent

class CryptoApp: Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}