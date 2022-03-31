package com.alif.notesapp.presentation.detail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.alif.notesapp.MainActivity
import com.alif.notesapp.R
import com.alif.notesapp.databinding.FragmentDetailBinding
import com.alif.notesapp.presentation.NotesViewModel
import com.alif.notesapp.utills.ExtensionFunctions.setActionBar

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding

    private val args by navArgs<DetailFragmentArgs>()
    private val detailViewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.toolbarDetail.setActionBar(requireActivity())
        binding.detail = args

        /*binding.apply {
            tvTitleDetail.text = args.notes.title
            tvDescriptionDetail.text = args.notes.desc
            tvDateDetail.text = args.notes.date
        }*/

        Log.d("detail", args.notes.title)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_edit -> {
                // go to update fragment
                val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(args.notes)
                findNavController().navigate(action)
            }
            R.id.menu_delete -> confirmDeleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteNote() {
        AlertDialog.Builder(context)
            .setTitle("Delete '${args.notes.title}'")
            .setMessage("Are You Sure Want To Delete This '${args.notes.title}'?")
            .setPositiveButton("Yes") { _, _ ->
                detailViewModel.deleteNote(args.notes)
                Toast.makeText(context, "Successfully Delete '${args.notes.title}'", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
            .setNegativeButton("No") { _, _ -> }
            .setNeutralButton("Cancel") { _, _ -> }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}