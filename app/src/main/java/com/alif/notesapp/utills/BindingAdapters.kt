package com.alif.notesapp.utills

import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.alif.notesapp.R
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.data.local.Priority
import com.alif.notesapp.presentation.home.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView

object BindingAdapters {

    @BindingAdapter("android:parsePriorityColor")
    @JvmStatic
    fun parsePriorityColor(cardView: MaterialCardView, priority: Priority) {
        when (priority) {
            Priority.HIGH -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.pink))
            Priority.MEDIUM -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
            Priority.LOW -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
        }
    }

    @BindingAdapter("android:sendDataToDetail")
    @JvmStatic
    fun sendDataToDetail(view: ConstraintLayout, currentItem: Notes) {
        view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currentItem)
            view.findNavController().navigate(action)
        }
    }

    @BindingAdapter("android:parsePriorityToInt")
    @JvmStatic
    fun parsePriorityToInt(view: AppCompatSpinner, priority: Priority) {
        when (priority) {
            Priority.HIGH -> {
                view.setSelection(0)
            }
            Priority.MEDIUM -> {
                view.setSelection(1)
            }
            Priority.LOW -> {
                view.setSelection(2)
            }
        }
    }

    //  Yoenas
//    @BindingAdapter("android:emptyDatabase")
//    @JvmStatic
//    fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
//        when (emptyDatabase.value) {
//            true -> view.visibility = View.VISIBLE
//            else -> view.visibility = View.INVISIBLE
//        }
//    }
}