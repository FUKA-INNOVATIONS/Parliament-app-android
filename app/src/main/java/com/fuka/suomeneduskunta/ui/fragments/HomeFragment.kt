package com.fuka.suomeneduskunta.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fuka.suomeneduskunta.R
import com.fuka.suomeneduskunta.data.database.ParliamentDatabase
import com.fuka.suomeneduskunta.databinding.FragmentHomeBinding
import com.fuka.suomeneduskunta.ui.viewModels.HomeViewModel
import com.fuka.suomeneduskunta.utils.Injector
import com.fuka.suomeneduskunta.utils.application.SuomenEduskuntaApplication
import java.util.*

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        // Create viewModel
        val context = requireNotNull(this.activity).application
        val factory = Injector.provideHomeViewModelFactory(context)
        val viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        // Bind the layout and the ViewModel
        binding.viewModel = viewModel

        binding.updateMembersButton.setOnClickListener {
            viewModel.refreshAllMembersData()
        }

        // set binding lifecycle to ensure that ui gets updated
        binding.setLifecycleOwner(this)
        return binding.root
    }
}