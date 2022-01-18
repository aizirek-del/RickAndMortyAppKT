package com.example.rickandmortyapp.api

import com.example.rickandmortyapp.api.model.CharacterList
import com.example.rickandmortyapp.api.model.Characters
import retrofit2.Response

class Repository() {

    suspend fun getCharacters(): CharacterList {
        return RetrofitModule.api.getCharacters()
    }
    suspend fun getOneCharacter(id:Int): Characters {
        return RetrofitModule.api.getOneCharacter(id)
    }
    suspend fun  searchCharacters(name:String): CharacterList {
        return RetrofitModule.api.searchCharacters(name)
    }
}
