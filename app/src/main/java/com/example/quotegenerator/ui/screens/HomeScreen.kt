package com.example.quotegenerator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quotegenerator.ui.Quote
import com.example.quotegenerator.ui.components.FavouriteBanner
import com.example.quotegenerator.ui.components.QuoteCard
import com.example.quotegenerator.ui.theme.QuoteGeneratorTheme
import com.example.quotegenerator.ui.theme.tertiaryContainer

@Composable
fun HomeScreen(
    randomQuote: Quote,
    onFavouriteClick: () -> Unit,
    favouriteQuotesCount: Int,
    onClickGenerate: () -> Unit,
    onFavouriteQuoteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            FavouriteBanner(
                onClickFavouriteQuotes = onFavouriteClick,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Box(
                modifier = Modifier
                    .padding(end = 4.5.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(tertiaryContainer)
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    text = favouriteQuotesCount.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        QuoteCard(
            quote = randomQuote,
            onClickFavourite = onFavouriteQuoteClick,
            onClickGenerate = onClickGenerate,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    QuoteGeneratorTheme {
        HomeScreen(
            randomQuote = Quote(1, "This is Quote", "Me", true),
            onFavouriteClick = {},
            favouriteQuotesCount = 0,
            onClickGenerate = {},
            onFavouriteQuoteClick = {}
        )
    }
}