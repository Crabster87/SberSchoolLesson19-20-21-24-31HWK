package crabster.rudakov.sberschoollesson19hwk.ui.splashScreen


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import crabster.rudakov.sberschoollesson19hwk.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Класс, реализующий проверку тест-кейса, при котором в поле EditText вводятся
 * буквы или их сочетания, которые могут присутствовать в названии страны, и
 * производится фильтрация списка с выводом корректного результата
 * */
@LargeTest
@RunWith(AndroidJUnit4::class)
class TestOpenMatchedCountries {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun testOpenMatchedCountries() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(2500)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.filter_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("br"), closeSoftKeyboard())

        val textView = onView(
            allOf(
                withId(R.id.textView), withText("Brazil"),
                withParent(
                    allOf(
                        withId(R.id.layout),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Brazil")))

        val textView2 = onView(
            allOf(
                withId(R.id.textView), withText("Brunei"),
                withParent(
                    allOf(
                        withId(R.id.layout),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Brunei")))

        val textView3 = onView(
            allOf(
                withId(R.id.textView), withText("Congo-Brazzaville"),
                withParent(
                    allOf(
                        withId(R.id.layout),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Congo-Brazzaville")))

        val textView4 = onView(
            allOf(
                withId(R.id.textView), withText("Gibraltar"),
                withParent(
                    allOf(
                        withId(R.id.layout),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Gibraltar")))

        val textView5 = onView(
            allOf(
                withId(R.id.textView), withText("Virgin Islands-British"),
                withParent(
                    allOf(
                        withId(R.id.layout),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Virgin Islands-British")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

}
