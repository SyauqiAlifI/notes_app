package com.alif.notesapp.utills

import android.content.Context
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.alif.notesapp.MainActivity
import com.alif.notesapp.R
import com.google.android.material.appbar.MaterialToolbar

object ExtensionFunctions {
    fun MaterialToolbar.setActionBar(requireActivity: FragmentActivity) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(navController, appBarConfiguration)
        (requireActivity as MainActivity).setSupportActionBar(this)
        navController.addOnDestinationChangedListener {_, destination, _ ->
            when (destination.id) {
                R.id.updateFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                R.id.addFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                R.id.detailFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
            }
        }
    }

    fun setPriorityColor(context: Context, cardView: CardView) {
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val arrPriority = context.resources.getStringArray(R.array.priorities)

        val pink = ContextCompat.getColor(context, R.color.pink)
        val yellow = ContextCompat.getColor(context, R.color.yellow)
        val green = ContextCompat.getColor(context, R.color.green)

        when (this.selectedItem) {
            arrPriority[0] -> cardView.setCardBackgroundColor(pink)
            arrPriority[1] -> cardView.setCardBackgroundColor(yellow)
            arrPriority[2] -> cardView.setCardBackgroundColor(green)
        }
        return ""
    }
}