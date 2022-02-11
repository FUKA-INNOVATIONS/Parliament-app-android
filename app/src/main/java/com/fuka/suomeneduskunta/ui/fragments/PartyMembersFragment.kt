package com.fuka.suomeneduskunta.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fuka.suomeneduskunta.R
import com.fuka.suomeneduskunta.databinding.FragmentPartyMembersBinding
import com.fuka.suomeneduskunta.ui.viewModels.PartyMembersViewModel
import com.fuka.suomeneduskunta.utils.Injector
import com.fuka.suomeneduskunta.utils.adapters.PartyMemberItemListner
import com.fuka.suomeneduskunta.utils.adapters.PartyMembersItemAdapter

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */


class PartyMembersFragment : Fragment() {
    private lateinit var binding: FragmentPartyMembersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_members, container, false)

        // Get partyId argument sent by Parties fragment
        val args: PartyMembersFragmentArgs by navArgs()
        var partyId = args.partyId

        // Create viewModel
        val context = requireNotNull(this.activity).application
        val factory = Injector.providePartyMembersViewModelFactory(context, partyId)
        val viewModel = ViewModelProvider(this, factory).get(PartyMembersViewModel::class.java)

        // Binding Party members list adapter
        val adapter = PartyMembersItemAdapter(PartyMemberItemListner { hetkaId ->
            viewModel.onPartyMemberItemClicked(hetkaId)
        })

        // Navigation Observe -> click handler
        viewModel.navigateToMemberDetail.observe(viewLifecycleOwner, Observer { hetkaId ->
            hetkaId?.let {
                this.findNavController().navigate(PartyMembersFragmentDirections.actionPartyMembersFragmentToMebmerDetailFragment(hetkaId))
                viewModel.onPartyMemberItemNavigated()
            }
        })

        binding.membersList.adapter = adapter

        viewModel.partyMembers.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // to ensure that ui gets updated
        binding.setLifecycleOwner(this)
        return binding.root
    }
}