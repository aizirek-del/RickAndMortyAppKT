package com.example.rickandmortyapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.api.Repository
import com.example.rickandmortyapp.api.model.CharacterList
import com.example.rickandmortyapp.api.model.Characters
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: Repository) : ViewModel() {
    val listCharacters = MutableLiveData<List<Characters>>()
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Exception>()
    val oneCharacter = MutableLiveData<Characters>()
    val searchCharacters = MutableLiveData<CharacterList>()

    fun getCharacters() {
        viewModelScope.launch {
            try {
                progress.postValue(true)
                val characters = repository.getCharacters()
                listCharacters.postValue(characters.results)
            } catch (e: Exception) {
                e.printStackTrace()
                error.postValue(e)
            }
        }
    }
    fun getOneCharacter(id: Int) {
        viewModelScope.launch {
            try {
                val character = repository.getOneCharacter(id)
                oneCharacter.postValue(character)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun searchCharacters(name:String){
        viewModelScope.launch {
            try {
                val characters = repository.searchCharacters(name)
                searchCharacters.postValue(characters)
            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
}