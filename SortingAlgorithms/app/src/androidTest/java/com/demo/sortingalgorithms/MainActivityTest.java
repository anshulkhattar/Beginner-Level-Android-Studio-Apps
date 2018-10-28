package com.demo.sortingalgorithms;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void selectBubbleSortMethod() throws Exception {
        onView(withId(R.id.radioBubble))
                .perform(click())
                .check(matches(isChecked()));

        onView(withId(R.id.txtInputValues))
                .perform(typeText(" 3, 52, 12,94, 83,"))
                .check(matches(withText(" 3, 52, 12,94, 83,")));

        onView(withText(R.string.instructions)).perform(closeSoftKeyboard());

        onView(withId(R.id.btnSort)).perform(click());

        onView(withId(R.id.tvResult)).check(matches(withText("3, 12, 52, 83, 94")));
    }

    @Test
    public void selectMergeSortMethod() throws Exception {
        onView(withId(R.id.radioMerge))
                .perform(click())
                .check(matches(isChecked()));

        onView(withId(R.id.txtInputValues))
                .perform(typeText(" 3, 52, 12,94, 83,"))
                .check(matches(withText(" 3, 52, 12,94, 83,")));

        onView(withText(R.string.instructions)).perform(closeSoftKeyboard());

        onView(withId(R.id.btnSort)).perform(click());

        onView(withId(R.id.tvResult)).check(matches(withText("3, 12, 52, 83, 94")));
    }

    @Test
    public void selectQuickSortMethod() throws Exception {
        onView(withId(R.id.radioQuick))
                .perform(click())
                .check(matches(isChecked()));

        onView(withId(R.id.txtInputValues))
                .perform(typeText(" 3, 52, 12,94, 83,"))
                .check(matches(withText(" 3, 52, 12,94, 83,")));

        onView(withText(R.string.instructions)).perform(closeSoftKeyboard());

        onView(withId(R.id.btnSort)).perform(click());

        onView(withId(R.id.tvResult)).check(matches(withText("3, 12, 52, 83, 94")));
    }
}
