package com.ama_patrol.data.repository.note

import com.ama_patrol.data.NoteOrder
import com.ama_patrol.data.OrderType
import com.ama_patrol.data.localdatabase.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)
}