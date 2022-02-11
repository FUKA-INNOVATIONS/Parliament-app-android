package com.fuka.suomeneduskunta.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fuka.suomeneduskunta.data.database.models.Like

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Käsittelee eduskuntajäsenten tykkäyksiä
*
* */


@Dao
interface LikeDao {

    // update like
    @Query("UPDATE `like` SET like_status = 1 WHERE profile_id = :hetkaId ")
    fun updateLike(hetkaId: Int)

    // insert like
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun likeMember(like: Like)

    // get ONE parlaiament member related (user specific) like
    @Query("SELECT * FROM `like` WHERE profile_id = :profileId AND hetka_id = :hetkaId")
    fun getUserLike(profileId: Int, hetkaId: Int): Like

    // get ONE parlaiament member related TOTAL amount of likes
    @Query("SELECT COUNT(like_id) FROM `like` WHERE hetka_id = :hetkaId")
    fun getMemberLikesCount(hetkaId: Int): LiveData<Int>

    // Delete like
    @Query("DELETE FROM `like` WHERE profile_id = :profileId AND hetka_id = :hetkaId")
    fun deletUserLike(profileId: Int, hetkaId: Int)
}