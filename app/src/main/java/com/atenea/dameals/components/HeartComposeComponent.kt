package com.atenea.dameals.components

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.atenea.dameals.R


@Composable
fun HeartComposeComponent(favorite: Boolean) {
    Icon(
        painter =
        if (favorite)
            painterResource(id = R.drawable.heart)
        else
            painterResource(id = R.drawable.empty_heart),
        contentDescription = "Favorite"
    )
}