package com.fuka.suomeneduskunta.ui.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuka.suomeneduskunta.ui.viewModels.PartiesViewModel
import com.fuka.suomeneduskunta.ui.viewModels.PartyMembersViewModel

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class PartyMembersViewModelFactory(private val application: Application, private val partyId: String) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PartyMembersViewModel(application, partyId) as T
    }

}