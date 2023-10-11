package com.example.quotegenerator.ui

data class FavouriteUiState(
    val favouriteQuotes: List<Quote> = emptyList(),
    val query: String = ""
)

data class Quote(
    val id: Int = 0,
    val quote: String = "",
    val author: String = "",
    val isFavourite: Boolean = false
)