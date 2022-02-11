package com.fuka.suomeneduskunta.data.repositories

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.fuka.suomeneduskunta.data.api.ParliamentApi
import com.fuka.suomeneduskunta.data.database.dao.HomeDao
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.fuka.suomeneduskunta.utils.application.SuomenEduskuntaApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Käsittelee alku-näkymää
* Huom! Satunnaisen edistajan luomiseen tarkoitettu painike on poistettu layoutista  -> Petri ja Peter tietävät tästä!
*
* Käyttäjän halutessa voidaan päivittää edustajien tietoja ajantasaisiks -> Manuaalinen tietojen päivitys
*
* */




class HomeRepository private constructor(private val homeDao: HomeDao){

    // Retireve all Members data from the database
    fun membersData(): LiveData<List<ParliamentMember>> {
        return homeDao.getMembers()
    }

    // // Retrieve 1 random hetkaID for generating Random user on Home fragment
    fun getRandomHetkaId(): LiveData<Int> {
        return homeDao.getRandomHetkaId()
    }

    // Get all app user likes -> return liked members hetkaId
    fun getLikedMembers(profileId: Int): LiveData<List<Int>> {
        return homeDao.getLikedMembers(profileId)
    }

    // Refres Members table with new Data fethed from the Api
    suspend fun refreshMembersDataFromApi() {

        // Fetch data from the Api
        val getMembersDeferred = ParliamentApi.service.getAllMembers()
        try {
            val members = getMembersDeferred.await()

            // Will be completed later, this will save parties shortName and sum of members into db table Party
            val parties = members.distinctBy { it.party }

            // Insert Fetched data in to the database
            withContext(Dispatchers.IO) {
                homeDao.insertAll(members)
                Log.i("FINFIN", "User manually refreshed the database with ${members.size} members")
            }
        } catch (e: Exception) {
            Log.d("FINFIN", "ParliamentApi Failure: ${e}")
        }
    }

    // Singleton instantiation
    companion object {
        // Volatile, means that writes to this field are immediately made visible to other threads.
        @Volatile private var instance: HomeRepository? = null

        // Get Dao instance
        fun getInstace(homeDao: HomeDao) =
            instance ?: synchronized(this) {
                instance ?: HomeRepository(homeDao).also { instance = it }
            }
    }
}
