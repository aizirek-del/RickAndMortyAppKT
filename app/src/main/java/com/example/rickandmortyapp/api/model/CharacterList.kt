package com.example.rickandmortyapp.api.model

import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName("results")
    var results: List<Characters>
)