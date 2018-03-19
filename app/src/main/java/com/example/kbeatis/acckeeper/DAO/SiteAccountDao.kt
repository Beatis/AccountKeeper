package com.example.kbeatis.acckeeper.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.kbeatis.acckeeper.Entity.SiteAccount

/**
 * Created by kbeatis on 10.03.18.
 */
@Dao
interface SiteAccountDao {
    @Query("SELECT * FROM site_account_table")
    fun getAll(): LiveData<List<SiteAccount>>

    @Query("SELECT * FROM site_account_table where id = :searchId")
    fun getSiteAccountById(searchId: Long): SiteAccount

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(siteAccount: SiteAccount)

    @Update
    fun update(siteAccount: SiteAccount)

    @Delete
    fun delete(siteAccount: SiteAccount)

    @Query("DELETE FROM site_account_table")
    fun deleteAll()
}