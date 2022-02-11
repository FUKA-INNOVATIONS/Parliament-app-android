package com.fuka.suomeneduskunta.ui.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuka.suomeneduskunta.ui.viewModels.PartiesViewModel

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class PartiesViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PartiesViewModel(application) as T
    }

}