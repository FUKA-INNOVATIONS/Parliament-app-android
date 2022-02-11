package com.fuka.suomeneduskunta.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.fuka.suomeneduskunta.data.database.ParliamentDatabase
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


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    // En ymmärrä miksi sovellus kaatuu jos poistan alla olevan rivin
    val homeDao = ParliamentDatabase.getInstance(application).homeDao

    // Fake profile id
    val profileId = 1


    // Get user liked Members hetkaIds
    val userLikes: LiveData<List<Int>>

    // Get random hetkaId
    val randomHetkaId: LiveData<Int> = Injector.getHomeRespository().getRandomHetkaId()


    init {
        userLikes = Injector.getHomeRespository().getLikedMembers(profileId)
    }



    // Create a coroutine Job/Scope fot manual refreshing of member data
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun refreshAllMembersData() {
        coroutineScope.launch {
            Injector.getHomeRespository().refreshMembersDataFromApi()
        }
    }

    // Stop Coroutine job
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}