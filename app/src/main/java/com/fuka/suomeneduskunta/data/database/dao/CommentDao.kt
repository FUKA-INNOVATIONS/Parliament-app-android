package com.fuka.suomeneduskunta.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fuka.suomeneduskunta.data.database.models.Comment
import com.fuka.suomeneduskunta.data.database.models.Like

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Käsittelee tietokannassa olevaa "comment" taulua joka littyy käyttäjien lisäämiin palautteisiin
*
* */


@Dao
interface CommentDao {

    // insert comment
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: Comment)

    // get ONE parlaiament member related comments
    // Order by Date > Newest
    @Query("SELECT * FROM `comment` WHERE hetka_id = :hetkaId ORDER BY comment_date DESC")
    fun getMemberComments(hetkaId: Int): LiveData<List<Comment>>

    // Delete one comment
    @Query("DELETE FROM `comment` WHERE profile_id = :profileId AND hetka_id = :hetkaId")
    fun deleteMemberComment(profileId: Int, hetkaId: Int)
}