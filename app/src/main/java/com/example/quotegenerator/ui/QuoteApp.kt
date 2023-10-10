package com.example.quotegenerator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quotegenerator.ui.screens.FavouriteScreen
import com.example.quotegenerator.ui.screens.HomeScreen
import com.example.quotegenerator.ui.theme.primary

enum class QuoteScreen {
    Home,
    Favourite
}

@Composable
fun QuoteApp(
    viewModel: FavouriteViewModel = FavouriteViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val randomQuote = viewModel.quote.value
    val favouriteQuotes = viewModel.uiState.value.favouriteQuotes
    NavHost(
        navController = navController,
        startDestination = QuoteScreen.Home.name,
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
    ) {
        composable(QuoteScreen.Home.name) {
            HomeScreen(
                randomQuote = randomQuote,
                onFavouriteClick = {
                    navController.navigate(QuoteScreen.Favourite.name)
                },
                favouriteQuotesCount = favouriteQuotes.size,
                onClickGenerate = {
                    viewModel.getRandomQuote()
                }
            )
        }
        composable(QuoteScreen.Favourite.name) {
            FavouriteScreen(
                favouriteQuotes = favouriteQuotes,
                onBackBannerClick = {
                    navController.navigate(QuoteScreen.Home.name)
                }
            )
        }
    }
}