package com.example.quotegenerator.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quotegenerator.ui.Quote
import com.example.quotegenerator.ui.components.BackToHomeBanner
import com.example.quotegenerator.ui.components.QuoteCard
import com.example.quotegenerator.ui.components.SearchTextField

@Composable
fun FavouriteScreen(
    favouriteQuotes: List<Quote>,
    query: String,
    onBackBannerClick: () -> Unit,
    onSearchChange: (String) -> Unit,
    onClickRemoveFavourite: (Quote) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(20.dp),
    ) {
        item {
            BackToHomeBanner(
                onClick = onBackBannerClick
            )
            Spacer(modifier = Modifier.padding(5.dp))
            SearchTextField(
                value = query,
                onValueChange = { onSearchChange(it) },
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
        items(favouriteQuotes.size) { index ->
            QuoteCard(
                quote = favouriteQuotes[index],
                onClickFavourite = { onClickRemoveFavourite(favouriteQuotes[index]) },
                isListItem = true,
                onClickRemoveFavourite = { onClickRemoveFavourite(favouriteQuotes[index]) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }


}