package com.example.notesfeature.internal.noteslist

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.backend_mock.Data
import com.example.backend_mock.Note
import com.example.notesfeature.TestBackend
import com.example.notesfeature.utils.DataBindingIdlingResourceRule
import com.example.notesfeature.utils.DispatchersRule
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteListTest {

    @get:Rule
    val activityRule = ActivityTestRule(NotesListTestActivity::class.java, false, false)

    @get:Rule
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    @get:Rule
    val dispatcherRule = DispatchersRule()

    @Test
    fun openFirstNote() {
        TestBackend.setResponseForPath("/notes", Json.encodeToString(fiveNoteList))
        TestBackend.setResponseForPath("/notes/1", Json.encodeToString(fiveNoteList.notes[0]))

        activityRule.launchActivity(null)

        notes {
            isVisible()
            clickOnItem(0)
        }
    }

    @Test
    fun notesListIsEmpty() {
        TestBackend.setResponseForPath("/notes", Json.encodeToString(emptyList))
        // Test when we get an empty result

        activityRule.launchActivity(null)

        notes {
            containsThisManyItems(0)
        }
    }

    @Test
    fun notesListContains5Items() {
        TestBackend.setResponseForPath("/notes", Json.encodeToString(fiveNoteList))

        activityRule.launchActivity(null)

        notes {
            containsThisManyItems(5)
        }
    }

    private val emptyList = Data(mutableListOf())
    private val fiveNoteList = Data(
        mutableListOf(
            Note(1, "", ""),
            Note(2, "", ""),
            Note(3, "", ""),
            Note(4, "", ""),
            Note(5, "", ""),
        )
    )
}
