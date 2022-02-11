package com.fuka.suomeneduskunta.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* "Puolueet" näkymän liittyvä teitokantakyselyt
*
* */


@Dao
interface PartiesDao {

    // Get Party names
    @Query("SELECT DISTINCT party FROM parliament_member")
    fun getPartyNames(): LiveData<List<String>>

    // Get sum of specific party members
    @Query("SELECT COUNT(*) FROM parliament_member WHERE party = :partyId")
    fun getmembersCounter(partyId: String): LiveData<Int>

    // get Parties by returning a unique member of each party
    @Query("SELECT * FROM parliament_member GROUP BY party")
    fun getParties(): LiveData<List<ParliamentMember>>





}