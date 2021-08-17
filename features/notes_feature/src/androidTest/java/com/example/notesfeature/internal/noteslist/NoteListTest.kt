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
import org.junit.Before
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

    private lateinit var testBackend: TestBackend

    @Before
    fun before() {
        activityRule.launchActivity(null)
        testBackend = activityRule.activity.backendCommunication as TestBackend
    }

    @Test
    fun openFirstNote() {
        testBackend.setResponseForPath("/notes", Json.encodeToString(fiveNoteList))
        testBackend.setResponseForPath("/notes/1", Json.encodeToString(fiveNoteList.notes[0]))
        notes {
            isVisible()
            clickOnItem(0)
        }
    }

    @Test
    fun notesListIsEmpty() {
        testBackend.setResponseForPath("/notes", Json.encodeToString(emptyList))
        // Test when we get an empty result
        notes {
            containsThisManyItems(0)
        }
    }

    @Test
    fun notesListContains5Items() {
        testBackend.setResponseForPath("/notes", Json.encodeToString(fiveNoteList))
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
