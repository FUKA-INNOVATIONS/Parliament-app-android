package com.fuka.suomeneduskunta.utils.application

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.fuka.suomeneduskunta.data.workers.RefreshMembersDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

// Override application to set up background work (refresh members data from api) via WorkManager
// Set in AndoirdManifest

class SuomenEduskuntaApplication : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Log.i("FINFIN", "From SuomenEduskuntaApplication.kt -> SuomenEduskuntaApplication created")
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshMembersDataWorker>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance().enqueueUniquePeriodicWork(RefreshMembersDataWorker.WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, repeatingRequest)
    }
}