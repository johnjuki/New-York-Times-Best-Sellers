package com.readingradar.android.ui.screens.book_details

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.readingradar.android.R
import com.readingradar.android.ui.DummyContent
import com.readingradar.android.ui.theme.ReadingRadarTheme
import com.readingradar.android.utils.Description
import org.junit.Rule
import org.junit.Test

class BookDetailsScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun initialState_isRendered() {
        composeTestRule.setContent {
            ReadingRadarTheme {
                BookDetailsScreen(uiState = BookDetailsUiState(), navigateUp = { })
            }
        }
        composeTestRule.onNodeWithContentDescription(Description.BOOK_DETAILS_LOADING)
            .assertIsDisplayed()
    }

    @Test
    fun stateWithContent_isRendered() {
        val book = DummyContent.book
        composeTestRule.setContent {
            ReadingRadarTheme {
                BookDetailsScreen(
                    uiState = BookDetailsUiState(isLoading = false, book = book),
                    navigateUp = {},
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(Description.BOOK_IMAGE).assertIsDisplayed()
        composeTestRule.onNodeWithText(book.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(book.contributor).assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.overview)).assertIsDisplayed()
    }

}
