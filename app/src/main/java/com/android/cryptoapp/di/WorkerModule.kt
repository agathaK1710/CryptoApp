package com.android.cryptoapp.di

import com.android.cryptoapp.data.workers.ChildWorkerFactory
import com.android.cryptoapp.data.workers.RefreshDetailWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(RefreshDetailWorker::class)
    fun bindRefreshDetailWorkerFactory(workerFactory: RefreshDetailWorker.Factory): ChildWorkerFactory
}