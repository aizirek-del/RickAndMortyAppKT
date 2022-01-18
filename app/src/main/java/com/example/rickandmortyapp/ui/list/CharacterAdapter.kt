package com.example.rickandmortyapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.api.model.Characters
import com.example.rickandmortyapp.databinding.ItemCharactersBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private val onItemClicked: (Characters) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {
    private var items = mutableListOf<Characters>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharactersBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(characters: List<Characters>) {
        items.clear()
        items.addAll(characters)
        notifyDataSetChanged()
    }
}

class CharacterViewHolder(
    private val binding: ItemCharactersBinding,
    private val onItemClicked: (Characters) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Characters) = with(binding) {
        characterId.text = character.id.toString()
        name.text = character.name
        Picasso.get().load(character.image).into(imageView)

        itemView.setOnClickListener { view ->
            onItemClicked(character)
        }
    }
}