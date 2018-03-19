package com.example.kbeatis.acckeeper.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by kbeatis on 07.03.18.
 */
@Entity(tableName = "user_table")
data class User(@PrimaryKey(autoGenerate = true) var id: Long?,
                @ColumnInfo(name = "login") var userLogin: String,
                @ColumnInfo(name = "password") var userPassword: String,
                @ColumnInfo(name = "secret_question") var secretQuestion: String,
                @ColumnInfo(name = "secret_answer") var secretAnswer: String,
                @ColumnInfo(name = "isAuthorized") var isAuthorized: Boolean) {

    constructor():this(null, "", "", "", "", true)
}