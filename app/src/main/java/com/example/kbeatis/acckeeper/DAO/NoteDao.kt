package com.example.kbeatis.acckeeper.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.kbeatis.acckeeper.Entity.Note

/**
 * Created by kbeatis on 10.03.18.
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table")
    fun getAll() : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()
}