package com.example.rickandmortyapp.repository

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.api.model.Characters
import com.example.rickandmortyapp.roomdao.ApiDao
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val localDataSource: ApiDao
){
    fun getCharacters(): LiveData<List<Characters>> {
        return localDataSource.getAllCharacters()
    }
    fun getOneCharacter(id: Int): LiveData<List<Characters>> {
        return localDataSource.getOneCharacter(id)
    }
    fun searchCharacters(name:String){
        localDataSource.searchCharacters(name)
    }
    }
