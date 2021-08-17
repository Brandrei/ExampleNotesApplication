package com.example.notesfeature.internal.noteslist

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.backend.BackendCommunication
import com.example.backend_mock.MockBackendCommunication
import com.example.notesfeature.TestBackend
import com.example.notesfeature.noteListFragment
import com.example.notesfeature.notesFragmentFactory

class NotesListTestActivity : FragmentActivity() {
    val backendCommunication: BackendCommunication = TestBackend()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = notesFragmentFactory(backendCommunication)
        super.onCreate(savedInstanceState)
        val layout = FrameLayout(this)
        layout.id = View.generateViewId()
        setContentView(layout)
        supportFragmentManager.commit {
            add(
                layout.id,
                noteListFragment(backendCommunication)
            )
        }
    }
}
