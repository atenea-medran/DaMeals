package com.atenea.dameals.presentation.favoritemeallist

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.atenea.dameals.mealModelDataBuilder
import org.junit.Rule
import org.junit.Test

class ShowFavoriteMealCardTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testShowMeal() {
        rule.setContent {
            ShowFavoriteMealCard(
                meal = mealModelDataBuilder(),
                onCardClick = {},
                onClickDelete = {}
            )
        }

        rule.onRoot().printToLog("MY TAG")

        rule.onNode(
            hasText("Teriyaki Chicken Casserole") and
                    hasClickAction()
        ).assertHasClickAction()
    }
}