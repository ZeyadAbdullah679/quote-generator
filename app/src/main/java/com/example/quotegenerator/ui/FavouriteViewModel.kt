package com.example.quotegenerator.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FavouriteViewModel : ViewModel() {

    private val _uiState = mutableStateOf(FavouriteUiState())
    val uiState: MutableState<FavouriteUiState> = _uiState

    private val _quote = mutableStateOf(Quote())
    val quote: MutableState<Quote> = _quote

    init {
        reset()
        getRandomQuote()
    }

    private fun getRandomQuote() {

    }

    fun onFavouriteClicked(quote: Quote) {

    }

    private fun reset() {

    }
}