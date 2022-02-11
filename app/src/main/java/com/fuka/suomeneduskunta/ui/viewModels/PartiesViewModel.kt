package com.fuka.suomeneduskunta.ui.viewModels

import android.app.Application
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


class PartiesViewModel(application: Application) : AndroidViewModel(application) {

    // Get all parties
    private val _parties: LiveData<List<ParliamentMember>>
    // Public getter
    val parties: LiveData<List<ParliamentMember>>
        get() = _parties

    init{
        _parties = Injector.getPartiesRepository().getParties()
    }



    // Click handler to move to partyMembers fragment
    private val _navigateToPartyMembersDetail = MutableLiveData<String?>()
    val navigateToPartyMembersDetail
        get() = _navigateToPartyMembersDetail

    fun onPartyItemClicked(partyId: String) {
        _navigateToPartyMembersDetail.value = partyId
    }

    fun onPartyItemNavigated() {
        _navigateToPartyMembersDetail.value = null
    }
}