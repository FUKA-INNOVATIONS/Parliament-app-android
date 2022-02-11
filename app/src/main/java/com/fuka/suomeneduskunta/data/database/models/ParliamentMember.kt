package com.fuka.suomeneduskunta.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Parliament members details are stored in this table
* Whole table content will pe updated once a day fetched from the Api
* */

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Esustajan tietokantataulun kuvaus/malli
*
* */

@Entity(tableName = "parliament_member")
data class ParliamentMember(
    @PrimaryKey @ColumnInfo(name = "hetka_id")
    val hetekaId: Int,
    @ColumnInfo(name = "seat_number")
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    @ColumnInfo(name = "picture_url")
    val pictureUrl: String
)