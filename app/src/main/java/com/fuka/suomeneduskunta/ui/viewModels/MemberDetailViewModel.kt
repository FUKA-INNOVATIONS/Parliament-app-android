package com.fuka.suomeneduskunta.ui.viewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.fuka.suomeneduskunta.data.database.ParliamentDatabase
import com.fuka.suomeneduskunta.data.database.models.Comment
import com.fuka.suomeneduskunta.data.database.models.Like
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.fuka.suomeneduskunta.utils.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */


class MemberDetailViewModel(application: Application, hetkaId: Int) : AndroidViewModel(application) {

    // Fake profile id
    val profileId = 1

    // Get member data
    var memberData = Injector.getMemberRepository().getMemberData(hetkaId)

    // Get total amout of member likes
    val memberLikesCount = Injector.getLikeRepository().getMemberLikesCount(hetkaId)

    // Get member comments
    var memberComments = Injector.getCommentRepository().getMemberComments(hetkaId)


    // Click handler to move delete domment
    private val _deleteComment = MutableLiveData<Int?>()
    val deleteComment
        get() = _deleteComment

    fun onDeleteCommentClicked(commentId: Int) {
        _deleteComment.value = commentId
    }

    fun onCommentDelete() {
        _deleteComment.value = null
    }

    init {
        // TODO: Get Profile data
    }



    // Member -> like button = Like or Unlike member
    fun likePressed() {
        val like = Like(likeId = 0, hetkaId = memberData.value!!.hetekaId, profileId, likeStatus = true)
        viewModelScope.launch {
            likeMemberHandler(like, memberData.value!!.hetekaId, profileId)
        }
    }

    suspend fun likeMemberHandler(like: Like, hetkaId: Int, profileId: Int) {
                Injector.getLikeRepository().likeMember(like, hetkaId, profileId)
    }
}
