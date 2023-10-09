package com.example.quotegenerator.ui

data class FavouriteUiState(
    val favouriteQuotes: List<Quote> = emptyList(),
)

data class Quote(
    val id: Int = 0,
    val quote: String = "",
    val author: String = "",
    val isFavourite: Boolean = false
)