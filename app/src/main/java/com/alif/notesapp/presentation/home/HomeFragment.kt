package com.alif.notesapp.presentation.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alif.notesapp.R
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.databinding.FragmentHomeBinding
import com.alif.notesapp.presentation.NotesViewModel
import com.alif.notesapp.utills.ExtensionFunctions.setActionBar

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private val homeViewModel: NotesViewModel by viewModels()
    private val homeAdapter by lazy { HomeAdapter() }

    private var _currentData: List<Notes>? = null
    private val currentData get() = _currentData as List<Notes>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.getAllNotes().observe(viewLifecycleOwner) {
            homeAdapter.setData(it)
        }

        setHasOptionsMenu(true)

        binding.apply {

            toolbarHome.setActionBar(requireActivity())

            fab.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addFragment)
            }
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvNotes.apply {
            homeViewModel.getAllNotes().observe(viewLifecycleOwner) {
                checkDataIsEmpty(it)
                homeAdapter.setData(it)
                _currentData = it
            }
            adapter = homeAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun checkDataIsEmpty(data: List<Notes>) {
        binding.apply {
            if (data.isEmpty()) {
                imgNoData.visibility = View.VISIBLE
                rvNotes.visibility = View.INVISIBLE
            } else {
                imgNoData.visibility = View.INVISIBLE
                rvNotes.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)

        val searchView = menu.findItem(R.id.menu_search)
        val actionView = searchView.actionView as? SearchView
        actionView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            searchNote(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            searchNote(it)
        }
        return true
    }

    private fun searchNote(query: String) {
        val querySearch = "%$query%"
        homeViewModel.searchNotesByQuery(querySearch).observe(this) {
            homeAdapter.setData(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_priority_high -> homeViewModel.sortByHighPriority.observe(this) { homeAdapter.setData(it) }
            R.id.menu_priority_low -> homeViewModel.sortByLowPriority.observe(this) { homeAdapter.setData(it) }
            R.id.menu_delete -> confirmDeleteAllData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteAllData() {
        if (currentData.isEmpty()) {
            AlertDialog.Builder(context)
                .setTitle("No Notes!")
                .setMessage("There is no Notes in your phone!")
                .setPositiveButton("OK") { _, _ -> }
        } else {
            AlertDialog.Builder(context)
                .setTitle("Delete All Notes?")
                .setMessage("Are You Sure Want To Delete All Notes?")
                .setPositiveButton("Yes") { _, _ ->
                    homeViewModel.deleteAllData()
                    Toast.makeText(context, "Successfully Delete All Notes", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { _, _ -> }
                .setNeutralButton("Cancel") { _, _ -> }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
