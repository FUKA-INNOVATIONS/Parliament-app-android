package com.fuka.suomeneduskunta.ui.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuka.suomeneduskunta.data.repositories.HomeRepository
import com.fuka.suomeneduskunta.ui.viewModels.HomeViewModel

/*
* @Suppress: Excludes the element from the generated documentation.
* Can be used for elements which are not part of the official API of a module but still have to be visible externally.
* */


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class HomeViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(application) as T
    }
}