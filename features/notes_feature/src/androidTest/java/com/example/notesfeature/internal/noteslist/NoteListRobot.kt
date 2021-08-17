package com.example.notesfeature.internal.noteslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.notesfeature.R
import org.hamcrest.CoreMatchers.both
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Description
import org.hamcrest.Matcher

inline fun notes(block: NoteListRobot.() -> Unit) = NoteListRobot().apply(block)

class NoteListRobot {

    fun isVisible() {
        onView(both(withId(R.id.list)).and(instanceOf(RecyclerView::class.java))).check(matches(isDisplayed()))
    }

    fun clickOnItem(position: Int) {
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))
    }

    fun containsThisManyItems(count: Int) {
        onView(withId(R.id.list)).check(matches(listWithThisManyItems(count)))
    }

}

private fun listWithThisManyItems(count: Int) = ListWithThisManyItems(count)

private class ListWithThisManyItems(private val count:Int) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    private var currentCount = -1
    override fun describeTo(description: Description) {
        description.appendText("RecyclerView adapter does not have $count items, it has $currentCount")
    }

    override fun matchesSafely(item: RecyclerView): Boolean {
        val adapter = item.adapter ?: return false
        currentCount = adapter.itemCount
        return adapter.itemCount == count
    }
}
