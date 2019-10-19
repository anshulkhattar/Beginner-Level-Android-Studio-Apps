package dev.ch8n.gittrends

import android.os.Looper.getMainLooper
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import dev.ch8n.gittrends.ui.home.MainActivity
import dev.ch8n.gittrends.utils.Utils
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
@MediumTest
@Config(
    application = TestApp::class
)
class MainActivityTest {

    /**
     * todo:
     * 1. Displaying a recycle view with content
     * 2. click on learn more opens bottom sheet dialog
     * 3. click on profile chip open preview activity
     */

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun cleanup() {
        shadowOf(getMainLooper()).idle()
    }

    @Test
    fun `is Activity opening without crash`() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { view ->
            Truth.assertThat(view.hasWindowFocus()).isTrue()
        }
    }

    // 1. Displaying a recycle view and has content
    @Test
    fun `is recycle view visible on activity has items`() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->
            Espresso.onView(ViewMatchers.withId(R.id.list_github_trending))
                .check { view, noViewFoundException ->
                    Truth.assertThat(view.isVisible).isTrue()
                }


            // waiting of network call would require idling resource setup
            activity.trendingListAdapter.submitList(Utils.getSampleTrendingData())

            //Idling resources but pollute code with Idling counters
            Thread.sleep(2000)

            Espresso.onView(ViewMatchers.withId(R.id.list_github_trending))
                .check { view, noViewFoundException ->
                    val reycleview = view as RecyclerView
                    Truth.assertThat(reycleview.adapter!!.itemCount).isAtLeast(3)
                }
        }
    }


    @Test
    fun `click on learn more opens bottom sheet dialog`() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->

            activity.trendingListAdapter.submitList(arrayListOf(Utils.getSampleTrendingData()[0]))

            Thread.sleep(2000)

            val chip = Espresso.onView(
                Matchers.allOf(
                    withId(R.id.chip_project_url), withText("Learn more"),
                    childAtPosition(
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.FrameLayout")),
                            0
                        ),
                        2
                    ),
                    ViewMatchers.isDisplayed()
                )
            )
            chip.perform(click())

            shadowOf(getMainLooper()).idleFor(1, TimeUnit.SECONDS)

            Thread.sleep(1000)

            Espresso.onView(withId(R.id.btn_preview_repo))
                .inRoot(withDecorView(not(`is`(activity.window.decorView))))
                .check { view, noViewFoundException ->
                    Truth.assertThat(view.isVisible).isTrue()
                }

        }

    }


    @Test
    fun `click on profile chip open preview activity`() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->

            activity.trendingListAdapter.submitList(arrayListOf(Utils.getSampleTrendingData()[0]))

            Thread.sleep(2000)

            val chip = Espresso.onView(
                Matchers.allOf(
                    withId(R.id.chip_project_url), withText("Learn more"),
                    childAtPosition(
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.FrameLayout")),
                            0
                        ),
                        2
                    ),
                    ViewMatchers.isDisplayed()
                )
            )
            chip.perform(click())

            val materialButton = Espresso.onView(
                Matchers.allOf(
                    withId(R.id.btn_preview_repo), withText("Preview Repository"),
                    childAtPosition(
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.FrameLayout")),
                            0
                        ),
                        1
                    ),
                    ViewMatchers.isDisplayed()
                )
            )
            materialButton.perform(click())

            Thread.sleep(700)

            val imageButton = Espresso.onView(
                Matchers.allOf(
                    withId(R.id.fab_preview_back),
                    childAtPosition(
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        ),
                        1
                    ),
                    ViewMatchers.isDisplayed()
                )
            )
            imageButton.check(matches(ViewMatchers.isDisplayed()))
        }

    }

}

