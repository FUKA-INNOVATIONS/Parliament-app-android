package com.fuka.suomeneduskunta.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Automaattisen edustajien tietojen päivitykseen/Workeriin liittyvä
* Lisää edustajien tiedot tietokantatauluun
*
* */


@Dao
interface WorkerDao {

    // Replace the members table with new info
    // vararg = function takes unknown arguments. Passes an anrray under the hood
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(parliamentMember: List<ParliamentMember>)
}