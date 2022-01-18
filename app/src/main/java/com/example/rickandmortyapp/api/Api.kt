package com.example.rickandmortyapp.api

import android.widget.TextView
import com.example.rickandmortyapp.api.model.CharacterList
import com.example.rickandmortyapp.api.model.Characters
import retrofit2.Response
import retrofit2.http.*
import java.lang.reflect.Type

interface Api {
    @GET("character")
    suspend fun getCharacters (): CharacterList

    @GET("character/{id}")
    suspend fun getOneCharacter(@Path("id") id: Int): Characters

    @GET("character")
    suspend fun searchCharacters(@Query("name") searchName: String): CharacterList
}