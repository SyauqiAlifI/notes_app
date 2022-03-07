package com.alif.notesapp.utills

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
            }
        }
    }
}