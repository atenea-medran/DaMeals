package com.atenea.dameals.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.stateDescription
import com.atenea.dameals.R
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.Red


@Composable
fun HeartComposeComponent(
    favorite: Boolean,
    onHeartClick: () -> Unit
) {
    Icon(
        modifier = Modifier
            .clickable {
                onHeartClick.invoke()
            }
            .clearAndSetSemantics {
                contentDescription = "Checkbox favorito"
                stateDescription = if (favorite) {
                    "Favorito marcado, toca para quitar de favoritos"
                } else {
                    "Favorito no marcado, toca para ponerlo en favoritos"
                }
            },
        tint = if (favorite) Red else Color.Black,
        painter =
        if (favorite) {
            painterResource(id = R.drawable.heart_filled)

        } else
            painterResource(id = R.drawable.heart_empty),
        contentDescription = "Favorite",
    )
}