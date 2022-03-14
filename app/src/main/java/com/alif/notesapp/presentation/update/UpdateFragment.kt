package com.alif.notesapp.presentation.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.alif.notesapp.MainActivity
import com.alif.notesapp.R
import com.alif.notesapp.databinding.FragmentUpdateBinding
import com.alif.notesapp.utills.ExtensionFunctions.setActionBar
import com.alif.notesapp.utills.ExtensionFunctions.setPriorityColor
import com.alif.notesapp.utills.HelperFunctions.setPriorityColor
import com.google.android.material.appbar.MaterialToolbar

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)

        binding.apply {
            binding.toolbarUpdate.setActionBar(requireActivity())
            spinnerPrioritiesUpdate.onItemSelectedListener = context?.let { setPriorityColor(it, priorityIndicator) }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_detailFragment2)
            Toast.makeText(context, "Note Has Been Updated.", Toast.LENGTH_SHORT).show()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


