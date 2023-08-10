package com.atenea.dameals.components

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.atenea.dameals.R
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.Red


@Composable
fun HeartComposeComponent(favorite: Boolean) {
    Icon(
        tint = if(favorite) Red else Color.Black,
        painter =
        if (favorite) {
            painterResource(id = R.drawable.heart_filled)

        }
        else
            painterResource(id = R.drawable.heart_empty),
        contentDescription = "Favorite"
    )
}