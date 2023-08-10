package com.atenea.dameals.presentation.login

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.atenea.dameals.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testLoginSuccess() {
        var logged = false
        rule.setContent {
            LoginScreen(
                onLoginSuccess = {
                    logged = true
                },
                onLoginFail = {}
            )
        }

        val loginText = rule.activity.getString(R.string.login)

        rule.onNodeWithTag(LOGIN_TEXT_FIELD_USER)
            .performTextInput("atenea-medran")

        rule.onNodeWithTag(LOGIN_TEXT_FIELD_PASSWORD)
            .performTextInput("contrasena")

        rule.onNodeWithText(loginText).performClick()

        assertThat(logged, `is`(true))
    }

    @Test
    fun testLoginFail() {
        var logged = false
        rule.setContent {
            LoginScreen(
                onLoginSuccess = {
                    logged = true
                },
                onLoginFail = {}
            )
        }
        val loginText = rule.activity.getString(R.string.login)

        rule.onNodeWithTag(LOGIN_TEXT_FIELD_USER)
            .performTextInput("atenea-medran")

        rule.onNodeWithTag(LOGIN_TEXT_FIELD_PASSWORD)
            .performTextInput("error")

        rule.onNodeWithText(loginText).performClick()

        assertThat(logged, `is`(false))

    }
}