package com.alif.notesapp.utills

import android.content.Context
import android.view.View
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.alif.notesapp.R
import com.alif.notesapp.data.local.Priority

object HelperFunctions {

    fun setPriorityColor(context: Context, cardView: CardView) : AdapterView.OnItemSelectedListener {

        val listener = object : AdapterView.OnItemSelectedListener {

//            val arrPriority = context.resources.getIntArray(R.array.priorities)

            val pink = ContextCompat.getColor(context, R.color.pink)
            val yellow = ContextCompat.getColor(context, R.color.yellow)
            val green = ContextCompat.getColor(context, R.color.green)

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> cardView.setCardBackgroundColor(pink)
                    1 -> cardView.setCardBackgroundColor(yellow)
                    2 -> cardView.setCardBackgroundColor(green)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        return listener
    }



    fun parseToPriority(context: Context?, priority: String): Priority {
        val arrayPriority = context?.resources?.getStringArray(R.array.priorities)
        return when (priority) {
            arrayPriority?.get(0) -> Priority.HIGH
            arrayPriority?.get(1) -> Priority.MEDIUM
            arrayPriority?.get(2) -> Priority.LOW
            else -> Priority.LOW
        }
    }
}