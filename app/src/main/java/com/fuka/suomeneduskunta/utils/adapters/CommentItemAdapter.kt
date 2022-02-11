package com.fuka.suomeneduskunta.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fuka.suomeneduskunta.data.database.models.Comment
import com.fuka.suomeneduskunta.databinding.ListItemCommentBinding
import com.fuka.suomeneduskunta.databinding.ListItemPartyBinding


/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */


class CommentItemAdapter(val clickListner: CommentItemListner) : ListAdapter<Comment, CommentItemAdapter.ViewHolder>(CommenttemDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!, clickListner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemCommentBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Comment, clickListner: CommentItemListner?) {
            binding.comment = item
            binding.clickListner = clickListner
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCommentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class CommenttemDiffCallback : DiffUtil.ItemCallback<Comment>() {

    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.commentId == newItem.commentId
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}

class CommentItemListner(val clickListener: (commentId: Int) -> Unit) {
    fun onClick(comment: Comment) = clickListener(comment.commentId)
}