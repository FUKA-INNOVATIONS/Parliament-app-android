package com.fuka.suomeneduskunta.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* "Puolueen jäseset" näkymän tietokantakyselyt
*
* */


@Dao
interface PartyMembersDao {

    //Get party specific members by party id
    @Query("SELECT * from parliament_member WHERE party = :partyId ORDER BY firstname ASC")
    fun getPartyMembers(partyId: String): LiveData<List<ParliamentMember>>

}
