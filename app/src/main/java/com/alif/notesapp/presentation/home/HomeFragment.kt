package com.alif.notesapp.presentation.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alif.notesapp.R
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.databinding.FragmentHomeBinding
import com.alif.notesapp.presentation.NotesViewModel
import com.alif.notesapp.utills.ExtensionFunctions.setActionBar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private val homeViewModel: NotesViewModel by viewModels()
    private val homeAdapter by lazy { HomeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            btnGoToDetail.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
            }
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvNotes.apply {
            homeViewModel.getAllNotes().observe(viewLifecycleOwner) {
                checkDataIsEmpty(it)
                homeAdapter.setData(it)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}