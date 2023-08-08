package com.atenea.dameals.presentation.favoritemeallist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteMealList(
    favoriteMealListViewModel: FavoriteMealListViewModel = koinViewModel()
) {
    val mealState = favoriteMealListViewModel.favoriteMealListFlow.collectAsStateWithLifecycle()

    favoriteMealListViewModel.getData()
    when (mealState.value) {
        is FavoriteMealListState.FavoriteMealList -> {
            LazyColumn(
                modifier = Modifier.padding(
                    vertical = 20.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val favoriteMealList =
                    (mealState.value as FavoriteMealListState.FavoriteMealList).favoriteMealList
                items(favoriteMealList.size) { i ->
                    val meal = favoriteMealList[i]
                    ShowFavoriteMealCard(meal) {
                        meal.favorite = false
                        favoriteMealListViewModel.removeMealFromFavoriteList(meal)
                    }
                }
            }
        }

        FavoriteMealListState.Idle -> {}
        FavoriteMealListState.Loading -> {}
    }
}