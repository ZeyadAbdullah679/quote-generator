package com.example.quotegenerator.ui

data class FavouriteUiState(
    val favouriteQuotes: List<Quote> = emptyList(),
    val query: String = ""
)

data class Quote(
    val id: String = "",
    val quote: String = "",
    val author: String = "",
    var isFavourite: Boolean = false
)