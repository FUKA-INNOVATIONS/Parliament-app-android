package com.fuka.suomeneduskunta.utils

import android.app.Application
import android.content.Context
import android.util.Log
import com.fuka.suomeneduskunta.data.database.ParliamentDatabase
import com.fuka.suomeneduskunta.data.database.dao.WorkerDao
import com.fuka.suomeneduskunta.data.repositories.*
import com.fuka.suomeneduskunta.ui.factories.HomeViewModelFactory
import com.fuka.suomeneduskunta.ui.factories.MemberDetailViewModelFactory
import com.fuka.suomeneduskunta.ui.factories.PartiesViewModelFactory
import com.fuka.suomeneduskunta.ui.factories.PartyMembersViewModelFactory
import com.fuka.suomeneduskunta.utils.application.SuomenEduskuntaApplication

/*
* This is Dependency injection meaning Seperating everything so that different parts are not depending on eachothers
* => Seperation of concerns!
* */


object Injector {
    // get application context
    val context = SuomenEduskuntaApplication()

    fun provideHomeViewModelFactory(application: Application): HomeViewModelFactory {
        return HomeViewModelFactory(application)
    }

    fun providePartiesViewModelFactory(application: Application): PartiesViewModelFactory {
        return PartiesViewModelFactory(application)
    }

    fun providePartyMembersViewModelFactory(applicaiton: Application, partyId: String): PartyMembersViewModelFactory {
        return PartyMembersViewModelFactory(applicaiton, partyId)
    }

    fun provideMemberDetailViewModelFactory(application: Application, hetkaId: Int): MemberDetailViewModelFactory {
        return MemberDetailViewModelFactory(application, hetkaId)
    }

    fun getHomeRespository(): HomeRepository {
        val homeDao = ParliamentDatabase.getInstance(context).homeDao
        return HomeRepository.getInstace(homeDao)
    }

    fun getPartiesRepository(): PartiesRepository {
        val partiesDao = ParliamentDatabase.getInstance(context).partiesDao
        return PartiesRepository.getInstace(partiesDao)
    }

    fun getPartyMembersRepository(): PartyMembersRepository {
        val partyMembersDao = ParliamentDatabase.getInstance(context).partyMembersDao
        return PartyMembersRepository.getInstace(partyMembersDao)
    }

    fun getMemberWorkerRepository(): WorkerRepository {
        val workerDao = ParliamentDatabase.getInstance(context).workerDao
        return WorkerRepository.getInstace(workerDao)
    }

    fun getMemberRepository(): MemberRepository {
        val memberDao = ParliamentDatabase.getInstance(context).memberDao
        return MemberRepository.getInstace(memberDao)
    }

    fun getLikeRepository(): LikeRepository {
        val likeDao = ParliamentDatabase.getInstance(context).likeDao
        return LikeRepository.getInstace(likeDao)
    }

    fun getCommentRepository(): CommentRepository {
        val commentDao = ParliamentDatabase.getInstance(context).commentDao
        return CommentRepository.getInstace(commentDao)
    }
}
