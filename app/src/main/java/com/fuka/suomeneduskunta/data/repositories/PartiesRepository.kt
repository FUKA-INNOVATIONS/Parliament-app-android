package com.fuka.suomeneduskunta.data.repositories

import androidx.lifecycle.LiveData
import com.fuka.suomeneduskunta.data.database.dao.PartiesDao
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class PartiesRepository private constructor(private val partiesDao: PartiesDao){

    fun getParties(): LiveData<List<ParliamentMember>> { return partiesDao.getParties() }

    // Get party names from the db
    val getPartyNames: LiveData<List<String>> = partiesDao.getPartyNames()

    //  get members counter
    fun getPartyMembersCounter(partyId: String) : LiveData<Int> {
        return partiesDao.getmembersCounter(partyId)
    }


    // Singleton instantiation
    companion object {
        // Volatile, means that writes to this field are immediately made visible to other threads.
        @Volatile private var instance: PartiesRepository? = null

        // Get Dao instance
        fun getInstace(partiesDao: PartiesDao) =
            instance ?: synchronized(this) {
                instance ?: PartiesRepository(partiesDao).also { instance = it }
            }
    }
}
