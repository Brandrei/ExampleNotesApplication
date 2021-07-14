package com.example.notesfeature.internal.notelist

import com.example.notesfeature.internal.notelist.service.NoteService
import com.example.notesfeature.internal.notelist.view.NoteListViewContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NoteListPresenter(
    val view: NoteListViewContainer,
    val navigation: NoteListNavigation,
    private val service: NoteService,
    scope: CoroutineScope
) : CoroutineScope by scope {
    init {
        loadNotes()
    }

    private fun loadNotes() {
        launch {
            val notes = service.getNotes()
            view.notes.postValue(notes)
        }
    }
}