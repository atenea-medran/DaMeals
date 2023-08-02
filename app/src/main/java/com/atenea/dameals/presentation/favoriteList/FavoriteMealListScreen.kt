package com.atenea.dameals.presentation.favoriteList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atenea.dameals.presentation.favoriteList.ui.theme.DaMealsTheme
import org.koin.androidx.compose.koinViewModel

class FavoriteMealListScreen() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaMealsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FavoriteMealList()
                }
            }
        }
    }
}

@Composable
fun FavoriteMealList(
    favoriteMealListViewModel: FavoriteMealListViewModel = koinViewModel()
) {
    val mealState = favoriteMealListViewModel.favoriteMealListFlow.collectAsStateWithLifecycle()

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
                items(favoriteMealList.size ?: 0) { i ->
                    val item = favoriteMealList[i]
                    ShowFavoriteMealList(item)
                }
            }
        }

        FavoriteMealListState.Idle -> {}
        FavoriteMealListState.Loading -> {}
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DaMealsTheme {
    }
}