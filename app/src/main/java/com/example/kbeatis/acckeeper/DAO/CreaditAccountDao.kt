package com.example.kbeatis.acckeeper.DAO

import android.arch.persistence.room.*
import com.example.kbeatis.acckeeper.Entity.CreditAccount

/**
 * Created by kbeatis on 10.03.18.
 */
@Dao
interface CreaditAccountDao {
    @Query("SELECT * FROM credit_account_table")
    fun getAll() : List<CreditAccount>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(creditAccount: CreditAccount)

    @Update
    fun update(creditAccount: CreditAccount)

    @Delete
    fun delete(creditAccount: CreditAccount)

    @Query("DELETE FROM credit_account_table")
    fun deleteAll()
}