package com.example.rickandmortyapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.api.Repository
import com.example.rickandmortyapp.api.model.Characters
import com.example.rickandmortyapp.databinding.FragmentListBinding
import com.example.rickandmortyapp.ui.SharedViewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel = SharedViewModel(Repository())
    private var adapter = CharacterAdapter(::onClickedCharacter)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.listCharacters.observe(viewLifecycleOwner) { response ->
            adapter.setItems(response)
        }
        sharedViewModel.progress.observe(viewLifecycleOwner) {

            if (it) txtApiError.isVisible = false
            swipeRefresh.isRefreshing
        }
        sharedViewModel.error.observe(viewLifecycleOwner) {
            txtApiError.text = it.message
            txtApiError.isVisible = true
        }
        recyclerView.layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )
        recyclerView.adapter = adapter
        sharedViewModel.getCharacters()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    sharedViewModel.searchCharacters(name = newText)
                }
                return false
            }
        })

        swipeRefresh.setOnRefreshListener {
            sharedViewModel.getCharacters()
        }
    }

    fun onClickedCharacter(character: Characters) {
        findNavController().navigate(
            R.id.action_listFragment_to_detailedFragment,
            bundleOf("id" to character.id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
