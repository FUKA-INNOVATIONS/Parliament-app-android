package com.fuka.suomeneduskunta.data.database.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fuka.suomeneduskunta.data.database.models.Like
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Käsittelee yksittäisen edustajan tietokanta kyselyitä -> member detail näkymä
*
* */

@Dao
interface MemberDao {

    // Retrieve and return member data from the database as LiveData
    @Query("SELECT * FROM parliament_member WHERE hetka_id = :hetkaId")
    fun getMemberData(hetkaId: Int): LiveData<ParliamentMember>

    // Solving Null issue
    // Get member data and return ParliamentMember object
    @Query("SELECT * FROM parliament_member WHERE hetka_id = :hetkaId LIMIT 1")
    suspend fun getMemberDataAsObject(hetkaId: Int): ParliamentMember



    // Update member data

    // List member comments/reviews

}
