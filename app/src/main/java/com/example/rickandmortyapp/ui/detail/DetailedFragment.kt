package com.example.rickandmortyapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmortyapp.api.Repository
import com.example.rickandmortyapp.api.model.Characters
import com.example.rickandmortyapp.databinding.FragmentDetailedBinding
import com.example.rickandmortyapp.ui.SharedViewModel
import com.squareup.picasso.Picasso

class DetailedFragment : Fragment() {
    private var _binding: FragmentDetailedBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel = SharedViewModel(Repository())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        val characterId = requireArguments().getInt("id", -1)
        sharedViewModel.getOneCharacter(characterId)
    }

    private fun setupObservers() {
        sharedViewModel.oneCharacter.observe(viewLifecycleOwner) { response ->
            bindCharacter(response)
        }
    }

    private fun bindCharacter(characters: Characters) {
        binding.nameDetailed.text = characters.name
        binding.speciesDetailed.text = characters.species
        binding.genderDetailed.text = characters.gender
        binding.statusDetailed.text = characters.status
        Picasso.get().load(characters.image).into(binding.image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}