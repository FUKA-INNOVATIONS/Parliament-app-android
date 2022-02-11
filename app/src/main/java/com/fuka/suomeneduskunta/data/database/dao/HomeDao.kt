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
* Käsittelee sovelluksen alku/ensimmäisem näkymän tietokantakyselyitä
*
* */


@Dao
interface HomeDao {

    // Retrieve and return all members from the database as LiveData
    @Query("SELECT * FROM parliament_member")
    fun getMembers(): LiveData<List<ParliamentMember>>

    // Retrieve 1 random hetkaID for generating Random user on Home fragment
    @Query("SELECT hetka_id AS random_hetka_id FROM parliament_member ORDER BY RANDOM()  LIMIT 1")
    fun getRandomHetkaId(): LiveData<Int>

    // Get all user likes -> return liked members hetkaId
    @Query("SELECT hetka_id FROM `like` WHERE profile_id = :profileId")
    fun getLikedMembers(profileId: Int) : LiveData<List<Int>>

    // Replace the members table with new info
    // vararg = function takes unknown arguments. Passes an anrray under the hood
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(parliamentMember: List<ParliamentMember>)

}