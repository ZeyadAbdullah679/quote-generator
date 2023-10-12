package com.example.quotegenerator.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotegenerator.network.QuoteApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface QuoteState {
    object Success : QuoteState
    object Error : QuoteState
    object Loading : QuoteState
    object Empty : QuoteState
}

class FavouriteViewModel : ViewModel() {

    private val _uiFavState = mutableStateOf(FavouriteUiState())
    val uiFavState: MutableState<FavouriteUiState> = _uiFavState

    private val _quote = mutableStateOf(Quote())
    val quote: MutableState<Quote> = _quote

    private val _quoteState: MutableState<QuoteState> = mutableStateOf(QuoteState.Empty)
    val quoteState: MutableState<QuoteState> = _quoteState

    private val _favouriteQuotesState: MutableState<QuoteState> = mutableStateOf(QuoteState.Empty)
    val favouriteQuotesState: MutableState<QuoteState> = _favouriteQuotesState


    init {
        getQuote()
    }

    fun getQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = QuoteApi.retrofitService.getRandomQuote()
                _quote.value = _quote.value.copy(
                    id = response.id,
                    quote = response.content,
                    author = response.author,
                    isFavourite = false
                )
            } catch (e: Exception) {
                Log.d("FavouriteViewModel", "Error: ${e.message}")
            }
        }
    }

    fun getFavouriteQuotes(ids: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            ids.forEach {
                try {
                    Log.d("FavouriteViewModel", it)
                    val response = QuoteApi.retrofitService.getQuoteById(it)
                    _uiFavState.value = _uiFavState.value.copy(
                        favouriteQuotes = _uiFavState.value.favouriteQuotes + Quote(
                            id = response.id,
                            quote = response.content,
                            author = response.author,
                            isFavourite = true
                        )
                    )
                } catch (e: Exception) {
                    Log.d("FavouriteViewModel", "Error: ${e.message}")
                }
            }
        }
    }

    fun addFavouriteQuote(): Boolean {
        if (_quote.value.isFavourite) {
            _uiFavState.value = _uiFavState.value.copy(
                favouriteQuotes = _uiFavState.value.favouriteQuotes
                    .filter { it.id != _quote.value.id })
            _quote.value = _quote.value.copy(isFavourite = false)
        } else {
            _quote.value = _quote.value.copy(isFavourite = true)
            if (!_uiFavState.value.favouriteQuotes.contains(_quote.value))
                _uiFavState.value = _uiFavState.value.copy(
                    favouriteQuotes = _uiFavState.value.favouriteQuotes + _quote.value
                )
        }
        return _quote.value.isFavourite
    }

    fun onSearchChange(query: String) {
        _uiFavState.value = _uiFavState.value.copy(query = query)
    }

    fun removeFavouriteQuote(quote: Quote) {
        _uiFavState.value.favouriteQuotes.find { quote.id == it.id }?.isFavourite = false
        _uiFavState.value = _uiFavState.value.copy(
            favouriteQuotes = _uiFavState.value.favouriteQuotes
                .filter { it.id != quote.id }
        )
        quote.isFavourite = false
    }
}