package com.readingradar.android.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
            HomeScreen(homeUiState = HomeUiState.Loading, onBookCoverClick = {})
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.loading))
            .assertIsDisplayed()
    }

    @Test
    fun stateWithContent_isRendered() {
        val bestSellersList = DummyContent.getBestSellersList()
        composeTestRule.setContent {
            HomeScreen(homeUiState = HomeUiState.Success(bestSellersList), onBookCoverClick = {})
        }
        composeTestRule.onNodeWithText(bestSellersList[0].displayName).assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription("Cover Image")
            .assertCountEquals(bestSellersList.size)
    }

    @Test
    fun stateWithContent_ClickOnBookCover_isRegistered() {
        val bestSellersList = DummyContent.getBestSellersList()
        val targetBook = bestSellersList[0]
        composeTestRule.setContent {
            HomeScreen(
                homeUiState = HomeUiState.Success(bestSellersList),
                onBookCoverClick = { isbn ->
                    assert(isbn == targetBook.books[0].isbn)
                },
            )
        }
        composeTestRule.onAllNodesWithContentDescription("Cover Image").onFirst().performClick()
    }
}
