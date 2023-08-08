package com.atenea.dameals.presentation.favoritemeallist

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.atenea.dameals.mealModelDataBuilder
import com.atenea.dameals.presentation.favoritemeallist.ShowFavoriteMealCard
import org.junit.Rule
import org.junit.Test

class ShowFavoriteMealCardTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testShowHero() {
        rule.setContent {
            ShowFavoriteMealCard(
                meal = mealModelDataBuilder()
            ) {}
        }

        rule.onRoot().printToLog("MY TAG")

        rule.onNode(
            hasText("Teriyaki Chicken Casserole") and
                    hasClickAction()
        ).assertHasClickAction()
    }
}