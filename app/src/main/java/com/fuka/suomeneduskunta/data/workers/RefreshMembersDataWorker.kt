package com.fuka.suomeneduskunta.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.fuka.suomeneduskunta.utils.Injector
import retrofit2.HttpException

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */


class RefreshMembersDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshMembersDataWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            Log.i("FINFIN", "from worker class -> Worker is is started")
            Injector.getMemberWorkerRepository().refreshMembersDataFromApi()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}