package com.fuka.suomeneduskunta.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fuka.suomeneduskunta.data.database.models.Comment
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.fuka.suomeneduskunta.databinding.ListItemPartyBinding

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class PartyItemAdapter(val clickListner: PartyItemListner) : ListAdapter<ParliamentMember, PartyItemAdapter.ViewHolder>(PartyItemDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!, clickListner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemPartyBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: ParliamentMember, clickListner: PartyItemListner?) {
            binding.party = item
            binding.clickListner = clickListner
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPartyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class PartyItemDiffCallback : DiffUtil.ItemCallback<ParliamentMember>() {

    override fun areItemsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem.party == newItem.party
    }

    override fun areContentsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem == newItem
    }
}

class PartyItemListner(val clickListener: (partyId: String) -> Unit) {
    fun onClick(member: ParliamentMember) = clickListener(member.party)
}