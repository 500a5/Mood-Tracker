package soft.divan.moodtracker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CreateMoodScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)


    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup()  {
        hiltRule.inject()
    }

    @Test
    fun testOpen() {
        composeTestRule.onNodeWithText("Аналитика").assertIsDisplayed()
    }
}
