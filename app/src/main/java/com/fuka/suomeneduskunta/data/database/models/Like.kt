package com.fuka.suomeneduskunta.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* When user likes a parliament member, we store it here
* */

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Tykkäysten tietokantataulun kuvaus/malli
*
* */



@Entity(tableName = "like")
data class Like(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "like_id")
    val likeId: Int,
    @ColumnInfo(name = "hetka_id")        // Add foregin key. later..
    val hetkaId: Int,
    @ColumnInfo(name = "profile_id")
    val profileId: Int,
    @ColumnInfo(name = "like_status")
    val likeStatus: Boolean
)