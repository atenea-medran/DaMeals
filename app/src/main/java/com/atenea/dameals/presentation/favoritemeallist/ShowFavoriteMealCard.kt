package com.atenea.dameals.presentation.favoritemeallist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.atenea.dameals.components.CheckAVSComponent
import com.atenea.dameals.domain.model.MealModel
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.globalElevation
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.globalPadding
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.globalRoundedCornerShape

@Composable
fun ShowFavoriteMealCard(
    meal: MealModel,
    onCardClick: () -> Unit,
    onClickDelete: () -> Unit
) {
    var done by remember {
        mutableStateOf(false)
    }

    var composeChecked by remember {
        mutableStateOf(false)
    }

    val requester = FocusRequester()

    Box{
        Card(
            modifier = Modifier
                .padding(globalPadding)
                .clickable {
                    onCardClick()
                }
                .background(Color.Gray),
            elevation = globalElevation,
            shape = RoundedCornerShape(globalRoundedCornerShape),

        ) {}
    }

    Row(
        modifier = Modifier
            .padding(globalPadding)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .focusRequester(focusRequester = requester)
                .focusable(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(meal.strMealThumb)
                .build(),
            contentDescription = "Meal ${meal.strMeal} Image"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.width(200.dp),
                    text = meal.strMeal ?: "",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
            Checkbox(
                modifier = Modifier
                    .clearAndSetSemantics {
                        contentDescription = "Marcar ${meal.strMeal} como cocinado"
                        stateDescription = if (composeChecked) {
                            "${meal.strMeal} marcado como cocinado"
                        } else {
                            "${meal.strMeal} desmarcado como cocinado"
                        }
                    }
                    .padding(end = 10.dp)
                    .focusable(),
                checked = composeChecked,
                onCheckedChange = { composeChecked = it }
            )
            Column {
                AndroidView(
                    modifier = Modifier
                        .clickable {
                            val newState = !done
                            done = newState
                        },
                    factory = { context ->
                        CheckAVSComponent(context).apply {
                            this.checked = done
                        }
                    },
                    update = {
                        it.checked = done
                    }
                )
                Spacer(modifier = Modifier.height(25.dp))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete icon",
                    Modifier.clickable { onClickDelete.invoke() }
                )
            }

        }
    }
}

