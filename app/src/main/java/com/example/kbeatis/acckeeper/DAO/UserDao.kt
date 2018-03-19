package com.example.kbeatis.acckeeper.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.kbeatis.acckeeper.Entity.User

/**
 * Created by kbeatis on 07.03.18.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAllLivedata(): LiveData<List<User>>

    @Query("SELECT * FROM user_table")
    fun getAll(): List<User>

    @Query("SELECT * FROM user_table where id = :searchId")
    fun getUserById(searchId: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()
}