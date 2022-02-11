package com.fuka.suomeneduskunta.data.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.fuka.suomeneduskunta.data.api.ParliamentApi
import com.fuka.suomeneduskunta.data.database.dao.HomeDao
import com.fuka.suomeneduskunta.data.database.dao.WorkerDao
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.fuka.suomeneduskunta.utils.application.SuomenEduskuntaApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class WorkerRepository private constructor(private val workerDao: WorkerDao){

    // Refres Members table with new Data fethed from the Api
    suspend fun refreshMembersDataFromApi() {

        // Fetch data from the Api
        val getMembersDeferred = ParliamentApi.service.getAllMembers()
        try {
            val members = getMembersDeferred.await()

            // Insert Fetched data in to the database
            withContext(Dispatchers.IO) {
                workerDao.insertAll(members)
                //Toast.makeText(this, "Worker refreshed DB", Toast.LENGTH_LONG).show()
            }
            Log.d("FINFIN", "Worker refreshed parliament_member table with ${members.size} new records.")

        } catch (e: Exception) {
            Log.d("FINFIN", "ParliamentApi Failure: ${e}")
        }
    }

    // Singleton instantiation
    companion object {
        // Volatile, means that writes to this field are immediately made visible to other threads.
        @Volatile private var instance: WorkerRepository? = null

        // Get Dao instance
        fun getInstace(workerDao: WorkerDao) =
            instance ?: synchronized(this) {
                instance ?: WorkerRepository(workerDao).also { instance = it }
            }
    }
}
