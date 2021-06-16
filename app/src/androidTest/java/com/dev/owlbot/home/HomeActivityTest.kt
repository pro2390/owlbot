package com.dev.owlbot.home


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.dev.owlbot.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun homeActivityTest() {
        val materialAutoCompleteTextView = onView(
allOf(withId(R.id.actSearch),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()))
        materialAutoCompleteTextView.perform(replaceText("owl"), closeSoftKeyboard())
        
        val materialAutoCompleteTextView2 = onView(
allOf(withId(R.id.actSearch), withText("owl"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()))
        materialAutoCompleteTextView2.perform(longClick())
        
        val materialAutoCompleteTextView3 = onView(
allOf(withId(R.id.actSearch), withText("owl"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()))
        materialAutoCompleteTextView3.perform(click())
        
        val materialAutoCompleteTextView4 = onView(
allOf(withId(R.id.actSearch), withText("owl"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()))
        materialAutoCompleteTextView4.perform(click())
        
        val materialAutoCompleteTextView5 = onView(
allOf(withId(R.id.actSearch), withText("owl"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()))
        materialAutoCompleteTextView5.perform(replaceText("dictionary"))
        
        val materialAutoCompleteTextView6 = onView(
allOf(withId(R.id.actSearch), withText("dictionary"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()))
        materialAutoCompleteTextView6.perform(closeSoftKeyboard())
        
        val materialAutoCompleteTextView7 = onView(
allOf(withId(R.id.actSearch), withText("dictionary"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()))
        materialAutoCompleteTextView7.perform(longClick())
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
