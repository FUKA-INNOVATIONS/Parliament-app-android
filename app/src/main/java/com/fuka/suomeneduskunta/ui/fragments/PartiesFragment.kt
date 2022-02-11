package com.fuka.suomeneduskunta.ui.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.fuka.suomeneduskunta.R
import com.fuka.suomeneduskunta.databinding.FragmentPartiesBinding
import com.fuka.suomeneduskunta.ui.viewModels.PartiesViewModel
import com.fuka.suomeneduskunta.utils.Injector
import com.fuka.suomeneduskunta.utils.adapters.PartyItemAdapter
import com.fuka.suomeneduskunta.utils.adapters.PartyItemListner
import com.fuka.suomeneduskunta.utils.application.SuomenEduskuntaApplication

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */


class PartiesFragment : Fragment() {
    private lateinit var binding: FragmentPartiesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parties, container, false)

        // Create viewModel
        val context = requireNotNull(this.activity).application
        val factory = Injector.providePartiesViewModelFactory(context)
        val viewModel = ViewModelProvider(this, factory).get(PartiesViewModel::class.java)

        // Add grid layout
        val layoutManager = GridLayoutManager(activity, 2)
        binding.partiesList.layoutManager = layoutManager

        // Create and bind adapter and pass party (shortName) as prameter
        val adapter = PartyItemAdapter(PartyItemListner { partyId ->
            viewModel.onPartyItemClicked(partyId)
        })
        binding.partiesList.adapter = adapter

        viewModel.navigateToPartyMembersDetail.observe(viewLifecycleOwner, Observer { partyId ->
            partyId?.let {
                this.findNavController().navigate(PartiesFragmentDirections.actionPartiesFragmentToPartyMembersFragment(partyId))
                viewModel.onPartyItemNavigated()
            }
        })

        viewModel.parties.observe(viewLifecycleOwner, Observer {
            it.let {
                // Sekoitetaan pakka reiluuden nimissä
                adapter.submitList(it.shuffled())
            }
        })

        // to ensure that ui gets updated
        binding.setLifecycleOwner(this)

        return binding.root
    }
}