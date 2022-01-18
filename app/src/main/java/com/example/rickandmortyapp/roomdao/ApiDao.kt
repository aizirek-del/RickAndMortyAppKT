package com.example.rickandmortyapp.roomdao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Query
import com.example.rickandmortyapp.api.model.Characters

@Dao
@Entity(tableName = "characters")
interface ApiDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters() : LiveData<List<Characters>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getOneCharacter(id: Int): LiveData<List<Characters>>

    @Query("SELECT * FROM characters WHERE name LIKE :name ")
    fun searchCharacters(name:String):LiveData<List<Characters>>

}