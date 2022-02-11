package com.fuka.suomeneduskunta.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.fuka.suomeneduskunta.utils.Injector


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */


class PartyMembersViewModel(application: Application, partyId: String) : AndroidViewModel(application) {


    //val partyMembers: LiveData<List<ParliamentMember>>?
    val partyMembers = Injector.getPartyMembersRepository().getPartyMembers(partyId)


    // Click handler to move to partyMembers fragment
    private val _navigateToMemberDetail = MutableLiveData<Int?>()
    val navigateToMemberDetail: LiveData<Int?>
        get() = _navigateToMemberDetail

    fun onPartyMemberItemClicked(hetkaId: Int) {
        _navigateToMemberDetail.value = hetkaId
    }

    fun onPartyMemberItemNavigated() {
        _navigateToMemberDetail.value = null
    }
}