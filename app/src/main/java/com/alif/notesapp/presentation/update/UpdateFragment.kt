package com.alif.notesapp.presentation.update

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alif.notesapp.R
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.databinding.FragmentUpdateBinding
import com.alif.notesapp.presentation.NotesViewModel
import com.alif.notesapp.utills.ExtensionFunctions.setActionBar
import com.alif.notesapp.utills.HelperFunctions.parseToPriority
import com.alif.notesapp.utills.HelperFunctions.setPriorityColor
import java.util.*

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding

    private val args by navArgs<UpdateFragmentArgs>()
    private val updateViewModel by viewModels<NotesViewModel>()

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
        binding.updateArgs = args

        binding.apply {
            binding.toolbarUpdate.setActionBar(requireActivity())
            spinnerPrioritiesUpdate.onItemSelectedListener =
                context?.let { setPriorityColor(it, priorityIndicator) }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            updateNote()
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updateNote() {
        binding.apply {
            val title = binding.edtTitleUpdate.text.toString()
            val desc = binding.edtDescriptionUpdate.toString()
            val priority = spinnerPrioritiesUpdate.selectedItem.toString()

            val date = Calendar.getInstance().time
            val formattedDate = SimpleDateFormat("dd,MM,yyyy", Locale.getDefault()).format(date)
            val note = Notes(
                args.update.id,
                title,
                desc,
                formattedDate,
                parseToPriority(context, priority)
            )
            updateViewModel.updateNote(note)
        }
        val action = UpdateFragmentDirections.actionUpdateFragmentToDetailFragment2(args.update)
        findNavController().navigate(action)
        Toast.makeText(context, "Note Has Been Updated.", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


