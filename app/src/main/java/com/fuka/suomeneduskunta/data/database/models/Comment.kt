package com.fuka.suomeneduskunta.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

/*
* User can add parliament member related comments and they are stored in this table
* */

// TODO: Note max input and other attributes: Secutrity and integrity issues!


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Palautteiden tietokantataulun kuvaus/malli
*
* */

@Entity(tableName = "comment")
data class Comment (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "comment_id")
    val commentId: Int,
    @ColumnInfo(name = "profile_id")    // Add foreign key later
    val profileId: Int,
    @ColumnInfo(name = "hetka_id")    // Add foreign key later
    val hetkaId: Int,
    @ColumnInfo(name = "comment_content")
    val commentContent: String,
    @ColumnInfo(name = "comment_date")
    val commentDate: String
    )