package com.android.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class RefreshDetailWorkerFactory @Inject constructor(
    private val workersProvider: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when(workerClassName){
            RefreshDetailWorker::class.qualifiedName -> {
                val child = workersProvider[RefreshDetailWorker::class.java]?.get()
                child?.create(appContext, workerParameters)
            }
            else -> null
        }
    }
}