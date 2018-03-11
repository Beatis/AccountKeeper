package com.example.kbeatis.acckeeper.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by kbeatis on 10.03.18.
 */
@Entity(tableName = "credit_account_table")
data class CreditAccount (@PrimaryKey(autoGenerate = true) var id: Long?,
                          @ColumnInfo(name = "credit_name") var creditName: String,
                          @ColumnInfo(name = "credit_number") var creditNumber: String,
                          @ColumnInfo(name = "credit_expired_month") var creditExpiredMonth: String,
                          @ColumnInfo(name = "credit_expired_year") var creditExpiredYear: String,
                          @ColumnInfo(name = "credit_cww") var creditCWW: String) {
    constructor():this(null, "", "", "", "", "")
}