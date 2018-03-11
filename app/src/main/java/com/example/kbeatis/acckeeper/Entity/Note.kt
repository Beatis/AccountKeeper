package com.example.kbeatis.acckeeper.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by kbeatis on 10.03.18.
 */
@Entity(tableName = "note_table")
data class Note (@PrimaryKey(autoGenerate = true) var id: Long?,
                 @ColumnInfo(name = "title") var title: String,
                 @ColumnInfo(name = "description") var description: String) {
    constructor():this(null,"","")
}