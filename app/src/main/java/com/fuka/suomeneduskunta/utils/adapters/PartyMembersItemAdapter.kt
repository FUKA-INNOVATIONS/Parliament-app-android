package com.fuka.suomeneduskunta.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.fuka.suomeneduskunta.databinding.ListItemPartyMemberBinding


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */

class PartyMembersItemAdapter(val clickListner: PartyMemberItemListner) : ListAdapter<ParliamentMember, PartyMembersItemAdapter.ViewHolder>(PartyMemberItemDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!, clickListner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemPartyMemberBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: ParliamentMember, clickListner: PartyMemberItemListner) {
            binding.member = item
            binding.clickListner = clickListner
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPartyMemberBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class PartyMemberItemDiffCallback : DiffUtil.ItemCallback<ParliamentMember>() {

    override fun areItemsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem.hetekaId == newItem.hetekaId
    }


    override fun areContentsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem == newItem
    }
}

class PartyMemberItemListner(val clickListener: (hetkaId: Int) -> Unit) {
    fun onClick(member: ParliamentMember) = clickListener(member.hetekaId)
}