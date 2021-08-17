package com.example.notesfeature

import com.example.backend.BackendCommunication
import com.example.backend.Request
import com.example.backend.Response

class TestBackend : BackendCommunication {
    companion object {
        private val presetResponses: MutableMap<String, String> = mutableMapOf()

        fun setResponseForPath(path: String, response: String) {
            presetResponses[path] = response
        }
    }

    override fun execute(request: Request): Response {
        return presetResponses[request.path]?.let{
            Response(200, it)
        } ?: Response(404, "")
    }
}