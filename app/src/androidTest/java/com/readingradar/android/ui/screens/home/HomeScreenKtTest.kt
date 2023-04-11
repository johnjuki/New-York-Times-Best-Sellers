package com.readingradar.android.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.readingradar.android.R
import com.readingradar.android.ui.DummyContent
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun initialState_isRendered() {
        composeTestRule.setContent {
            HomeScreen(homeUiState = HomeUiState.Loading)
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.loading))
            .assertIsDisplayed()
    }

    @Test
    fun stateWithContent_isRendered() {
        val bestSellersList = DummyContent.getBestSellersList()
        composeTestRule.setContent {
            HomeScreen(homeUiState = HomeUiState.Success(bestSellersList))
        }
        composeTestRule.onNodeWithText(bestSellersList[0].displayName).assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription("Cover Image")
            .assertCountEquals(bestSellersList.size)
    }
}
