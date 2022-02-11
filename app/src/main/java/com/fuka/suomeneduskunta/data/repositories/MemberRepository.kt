package com.fuka.suomeneduskunta.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fuka.suomeneduskunta.data.database.dao.MemberDao
import com.fuka.suomeneduskunta.data.database.models.Like
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember



/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class MemberRepository private constructor(private val memberDao: MemberDao){

    // Retireve member data from the database
    fun getMemberData(hetkaId: Int): LiveData<ParliamentMember> {
        return memberDao.getMemberData(hetkaId)
    }

    // to solve the Wiered Null issue but its still returning null when calling it
    suspend fun getMemberDataAsObject(hetkaId: Int): ParliamentMember{
        return memberDao.getMemberDataAsObject(hetkaId)
    }


    // Singleton instantiation
    companion object {
        // Volatile, means that writes to this field are immediately made visible to other threads.
        @Volatile private var instance: MemberRepository? = null

        // Get Dao instance
        fun getInstace(memberDao: MemberDao) =
            instance ?: synchronized(this) {
                instance ?: MemberRepository(memberDao).also { instance = it }
            }
    }
}