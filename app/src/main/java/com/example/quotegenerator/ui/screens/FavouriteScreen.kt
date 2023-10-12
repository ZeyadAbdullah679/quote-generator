package com.example.quotegenerator.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quotegenerator.ui.FavouriteViewModel
import com.example.quotegenerator.ui.Quote
import com.example.quotegenerator.ui.components.BackToHomeBanner
import com.example.quotegenerator.ui.components.QuoteCard
import com.example.quotegenerator.ui.components.SearchTextField

@Composable
fun FavouriteScreen(
    viewModel: FavouriteViewModel,
    onBackBannerClick: () -> Unit,
    onSearchChange: (String) -> Unit,
    onClickRemoveFavourite: (Quote) -> Unit
) {
    val uiFavState = viewModel.uiFavState.value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        item {
            BackToHomeBanner(
                onClick = onBackBannerClick
            )
            Spacer(modifier = Modifier.padding(5.dp))
            SearchTextField(
                value = uiFavState.query,
                onValueChange = onSearchChange,
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }

        itemsIndexed(
            items = if (uiFavState.query.isEmpty()) uiFavState.favouriteQuotes else viewModel.searchFavState.value
        ) { _, item ->
            QuoteCard(
                quote = item,
                isListItem = true,
                onClickRemoveFavourite = onClickRemoveFavourite
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}