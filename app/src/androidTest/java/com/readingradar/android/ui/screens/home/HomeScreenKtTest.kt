package com.readingradar.android.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.readingradar.android.ui.DummyContent
import com.readingradar.android.ui.theme.ReadingRadarTheme
import com.readingradar.android.utils.Description
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun initialState_isRendered() {
        composeTestRule.setContent {
            ReadingRadarTheme {
                HomeScreen(homeUiState = HomeUiState.Loading, onBookImageClick = {})
            }
        }
        composeTestRule.onNodeWithContentDescription(Description.BOOKS_LOADING)
    }

    @Test
    fun stateWithContent_isRendered() {
        val bestSellersList = DummyContent.getBestSellersList()
        composeTestRule.setContent {
            ReadingRadarTheme {
                HomeScreen(homeUiState = HomeUiState.Success(bestSellersList), onBookImageClick = {})
            }
        }
        composeTestRule.onNodeWithText(bestSellersList[0].displayName).assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription(Description.BOOK_IMAGE)
            .assertCountEquals(bestSellersList.size)
    }

    @Test
    fun stateWithContent_ClickOnBookImage_isRegistered() {
        val bestSellersList = DummyContent.getBestSellersList()
        val targetBook = bestSellersList[0]
        composeTestRule.setContent {
            ReadingRadarTheme {
                HomeScreen(
                    homeUiState = HomeUiState.Success(bestSellersList),
                    onBookImageClick = { isbn ->
                        assert(isbn == targetBook.books[0].isbn)
                    },
                )
            }
        }
        composeTestRule.onAllNodesWithContentDescription(Description.BOOK_IMAGE).onFirst().performClick()
    }
}
