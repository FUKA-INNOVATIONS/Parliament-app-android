package com.fuka.suomeneduskunta.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.fuka.suomeneduskunta.data.api.ParliamentApi
import com.fuka.suomeneduskunta.data.database.dao.LikeDao
import com.fuka.suomeneduskunta.data.database.dao.MemberDao
import com.fuka.suomeneduskunta.data.database.models.Like
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
*
*
* */



class LikeRepository private constructor(private val likeDao: LikeDao) {

    // Get member total amount of likes
    fun getMemberLikesCount(hetkaId: Int): LiveData<Int> {
        return likeDao.getMemberLikesCount(hetkaId)
    }

    // likeMember
    suspend fun likeMember(like: Like, hetkaId: Int, profileId: Int) {
        // Check if user has already liked the member
        //val likeChek = likeDao.getUserLike(profileId, hetkaId)
        var likeChek: Like

        withContext(Dispatchers.IO) {
            // check if member is already liked
            val likeCheker = likeDao.getUserLike(profileId, hetkaId)
            likeChek = likeCheker
        }

        if (likeChek == null) {
            try {
                // Insert like in to the database incase like doesnt exist
                withContext(Dispatchers.IO) {
                    likeDao.likeMember(like)
                }

            } catch (e: Exception) {
            }
        } else {
            // remove like since user wants to unlike member
                withContext(Dispatchers.IO) {
                    likeDao.deletUserLike(profileId, hetkaId)
                }
        }
    }



    // Singleton instantiation
    companion object {
        // Volatile, means that writes to this field are immediately made visible to other threads.
        @Volatile private var instance: LikeRepository? = null

        // Get Dao instance
        fun getInstace(likeDao: LikeDao) =
            instance ?: synchronized(this) {
                instance ?: LikeRepository(likeDao).also { instance = it }
            }
    }
}