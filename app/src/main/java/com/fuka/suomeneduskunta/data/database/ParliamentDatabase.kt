package com.fuka.suomeneduskunta.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fuka.suomeneduskunta.data.database.dao.*
import com.fuka.suomeneduskunta.data.database.models.Comment
import com.fuka.suomeneduskunta.data.database.models.Like
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.fuka.suomeneduskunta.data.database.models.UserProfile


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* The database contained infrmation is devided into four tables and will be extended
* At the moment user_profile table is not used since it is part of the future functionalities
*
* TODO:  All database queries should be optimised!
*
* */




@Database(entities = [ParliamentMember::class, UserProfile::class, Like::class, Comment::class], version = 1, exportSchema = true)
abstract class ParliamentDatabase : RoomDatabase() {

    abstract val memberDao: MemberDao
    abstract val homeDao: HomeDao
    abstract val partiesDao: PartiesDao
    abstract val partyMembersDao: PartyMembersDao
    abstract val workerDao: WorkerDao
    abstract val likeDao: LikeDao
    abstract val commentDao: CommentDao

    companion object {
        @Volatile
        private var INSTANCE: ParliamentDatabase? = null

        fun getInstance(context: Context): ParliamentDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, ParliamentDatabase::class.java,"ParliamentDatabase")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance as ParliamentDatabase
            }
        }
    }
}