package com.atenea.dameals.presentation.favoriteList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    favoriteMealListViewModel: FavoriteMealListScreenViewModel = koinViewModel()
) {
    val mealState = favoriteMealListViewModel.favoriteMealList.observeAsState()

    LazyColumn(
        modifier = Modifier.padding(
            vertical = 20.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val favoriteMealList = mealState.value
        items(favoriteMealList?.size ?: 0) { i ->
            val item = favoriteMealList?.get(i)
            item?.let { meal ->
                ShowFavoriteMealList(meal)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DaMealsTheme {
    }
}