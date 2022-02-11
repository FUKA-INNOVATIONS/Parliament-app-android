package com.fuka.suomeneduskunta.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* App user profile details are stored in this table
* */


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* TODO: Sovelluksen käyttäjän tietokantataulun kuvaus/malli
*
* */


@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "profile_id")
    val profileId: Int,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    val email: String
)