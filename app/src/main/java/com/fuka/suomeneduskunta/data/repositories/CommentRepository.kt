package com.fuka.suomeneduskunta.data.repositories

import androidx.lifecycle.LiveData
import com.fuka.suomeneduskunta.data.database.dao.CommentDao
import com.fuka.suomeneduskunta.data.database.models.Comment

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* Käsittlee käyttäjien "edusjajille jätetyt" palautteet
*
* */



class CommentRepository private constructor(private val commentDao: CommentDao) {

    // Get member comments
    fun getMemberComments(hetkaId: Int): LiveData<List<Comment>> {
        return commentDao.getMemberComments(hetkaId)
    }

    // Insert new comment
    fun insertComment(comment: Comment) = commentDao.insertComment(comment)

    // Singleton instantiation
    companion object {
        // Volatile, means that writes to this field are immediately made visible to other threads.
        @Volatile private var instance: CommentRepository? = null

        // Get Dao instance
        fun getInstace(commentDao: CommentDao) =
            instance ?: synchronized(this) {
                instance ?: CommentRepository(commentDao).also { instance = it }
            }
    }
}