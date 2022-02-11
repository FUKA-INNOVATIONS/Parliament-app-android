package com.fuka.suomeneduskunta.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fuka.suomeneduskunta.R
import com.fuka.suomeneduskunta.data.database.models.Comment
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.fuka.suomeneduskunta.databinding.FragmentHomeBinding
import com.fuka.suomeneduskunta.databinding.FragmentMebmerDetailBinding
import com.fuka.suomeneduskunta.ui.viewModels.HomeViewModel
import com.fuka.suomeneduskunta.ui.viewModels.MemberDetailViewModel
import com.fuka.suomeneduskunta.ui.viewModels.PartiesViewModel
import com.fuka.suomeneduskunta.utils.Injector
import com.fuka.suomeneduskunta.utils.adapters.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.CoroutineContext

/*
* 9.3.2021
* Fuwad Kalhori
* 2008798
*
* */


class MemberDetailFragment : Fragment() {
    private lateinit var binding: FragmentMebmerDetailBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mebmer_detail, container, false)

        // Get hetkaId argument sent by PartyMembers fragment
        val args: MemberDetailFragmentArgs by navArgs()
        val hetkaId = args.hetkaId

        // Create viewModel
        val context = requireNotNull(this.activity).application
        val factory = Injector.provideMemberDetailViewModelFactory(context, args.hetkaId)
        val viewModel = ViewModelProvider(this, factory).get(MemberDetailViewModel::class.java)
        binding.viewModel = viewModel

        // Binding Comments list adapter
        //TODO: Implement delete functionality and move code to relevant places
        val adapter = CommentItemAdapter(CommentItemListner { commentId ->
            viewModel.onDeleteCommentClicked(commentId)
        })
        binding.commentList.adapter = adapter

        // Pass Comment Adapter data
       viewModel.memberComments.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Hide keyboard    TODO -> Slpit and Move to relevant places
        fun clearCommentBox() {
            // Clear editText
            binding.commentTextBox.text.clear()

            // hide comment box
            binding.commentBox.visibility = View.GONE

            // Hide keyboard
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0)

            // Show add button
            binding.writeCommentButton.visibility = View.VISIBLE
        }

        // Open -> Write new comment box
        // TODO -> hide keyboard and empty text box
        binding.writeCommentButton.setOnClickListener {
            binding.commentBox.visibility = View.VISIBLE
            binding.writeCommentButton.visibility = View.GONE
        }
        // Close comment box
        binding.closeCommentBoxButton.setOnClickListener {
            binding.commentBox.visibility = View.GONE
            binding.writeCommentButton.visibility = View.VISIBLE
            clearCommentBox()
        }

        // Add new comment to database   -> TODO -> Slpit and Move to relevant places
        suspend fun addNewComment() {
            viewLifecycleOwner.lifecycleScope.launch {

                val commentContent = binding.commentTextBox.text.toString()
                val date = Date().toString()    // TODO:  Reformat date
                val profileId = viewModel.profileId
                //Create New comment
                val newComment: Comment = Comment(0, profileId, hetkaId, commentContent, date)

                if (commentContent.length <= 10 ) {
                    // Show error message
                    Toast.makeText(context, "Kirjoita väh. 11 merkkiä!", Toast.LENGTH_SHORT).show()

                } else {
                    //Insert comment into the database
                    withContext(Dispatchers.IO) {
                        Injector.getCommentRepository().insertComment(newComment)
                    }
                    // Hide keyboard
                    clearCommentBox()

                    // Show success message
                    Toast.makeText(context, "Kiitos palautteesta!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Add new comment clickHandler TODO -> Move to relevant place
        binding.addCommentButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                addNewComment()
            }
        }

        // to ensure that ui gets updated
        binding.setLifecycleOwner(this)

        return binding.root
    }

}