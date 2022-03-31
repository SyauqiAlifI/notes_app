package com.alif.notesapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alif.notesapp.R
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.data.local.Priority
import com.alif.notesapp.databinding.RowItemNotesBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var listNotes = ArrayList<Notes>()

    inner class MyViewHolder(val binding: RowItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemNotesBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNotes[position]
        holder.binding.apply {
            mNotes = data
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setData(data: List<Notes>?) {
        if (data == null) return
        val diffCallback = DiffCallback(listNotes, data)
        val diffUtil = DiffUtil.calculateDiff(diffCallback)
        listNotes.clear()
        listNotes.addAll(data)
        diffUtil.dispatchUpdatesTo(this)
    }
}