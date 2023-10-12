package com.example.quotegenerator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    viewModel: FavouriteViewModel = remember { FavouriteViewModel() },
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    viewModel.getFavouriteQuotes(AppSharedPref(context).getIdsList())
    NavHost(
        navController = navController,
        startDestination = QuoteScreen.Home.name,
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
    ) {
        composable(QuoteScreen.Home.name) {
            HomeScreen(
                viewModel = viewModel,
                onFavouriteClick = {
                    navController.navigate(QuoteScreen.Favourite.name)
                },
                onClickGenerate = {
                    viewModel.getQuote()
                },
                onFavouriteQuoteClick = {
                    if (viewModel.addFavouriteQuote()) {
                        AppSharedPref(context).addIdToList(viewModel.quote.value.id)
                    } else {
                        AppSharedPref(context).removeIdFromList(viewModel.quote.value.id)
                    }
                }
            )
        }
        composable(QuoteScreen.Favourite.name) {
            FavouriteScreen(
                viewModel = viewModel,
                onBackBannerClick = {
                    navController.popBackStack()
                },
                onSearchChange = {
                    viewModel.onSearchChange(it)
                },
                onClickRemoveFavourite = {
                    viewModel.removeFavouriteQuote(it)
                    AppSharedPref(context).removeIdFromList(it.id)
                }
            )
        }
    }
}