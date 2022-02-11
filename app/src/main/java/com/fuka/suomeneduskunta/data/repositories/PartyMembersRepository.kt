package com.fuka.suomeneduskunta.data.repositories

import androidx.lifecycle.LiveData
import com.fuka.suomeneduskunta.data.database.dao.PartyMembersDao
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class PartyMembersRepository private constructor(private val partyMembersDao: PartyMembersDao){

    // Retireve all party specific members by partyId = db.table.partyName
    fun getPartyMembers(partyId: String) : LiveData<List<ParliamentMember>> {
        return partyMembersDao.getPartyMembers(partyId)
    }


    // Singleton instantiation
    companion object {
        // Volatile, means that writes to this field are immediately made visible to other threads.
        @Volatile private var instance: PartyMembersRepository? = null

        // Get Dao instance
        fun getInstace(partyMembersDao: PartyMembersDao) =
            instance ?: synchronized(this) {
                instance ?: PartyMembersRepository(partyMembersDao).also { instance = it }
            }
    }
}
