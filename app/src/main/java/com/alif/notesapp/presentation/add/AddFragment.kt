package com.alif.notesapp.presentation.add

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alif.notesapp.R
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.databinding.FragmentAddBinding
import com.alif.notesapp.presentation.NotesViewModel
import com.alif.notesapp.utills.ExtensionFunctions.setActionBar
import com.alif.notesapp.utills.HelperFunctions.parseToPriority
import com.alif.notesapp.utills.HelperFunctions.setPriorityColor
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding

    private val addViewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        binding.apply {
            toolbarAdd.setActionBar(requireActivity())
            spinnerPriorities.onItemSelectedListener =
                context?.let { setPriorityColor(it, priorityIndicator) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            insertNote()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
            Toast.makeText(context, "Successfully Add Note..", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertNote() {
        binding.apply {
            val title = edtTitle.text.toString()
            val desc = edtDescription.text.toString()

            val calendar = Calendar.getInstance().time
            val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calendar)
            val priority = spinnerPriorities.selectedItem.toString()

            if (edtTitle.text.isEmpty() || edtDescription.text.isEmpty()) {
                if (edtTitle.text.isEmpty()) {
                    edtTitle.error = "Please fill the field"

                } else if (edtDescription.text.isEmpty()) {
                    edtDescription.error = "Please fill the field"
                } else {
                    edtTitle.error = "Please fill the field"
                    edtDescription.error = "Please fill the field"
                }
                //  Toast.makeText(context, "Please fill the fields", Toast.LENGTH_SHORT).show()
            } else {
                val data = Notes(
                    0,
                    title,
                    desc,
                    date,
                    parseToPriority(context, priority)
                )
                addViewModel.insertNotes(data)
                findNavController().navigate(R.id.action_addFragment_to_homeFragment)
                Toast.makeText(context, "Successfully add note", Toast.LENGTH_SHORT).show()
                Log.i("AddFragment", "insertnote: $data")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }*/

}