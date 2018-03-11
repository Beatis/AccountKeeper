package com.example.kbeatis.acckeeper.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.sql.RowId

/**
 * Created by kbeatis on 10.03.18.
 */
@Entity(tableName = "site_account_table")
data class SiteAccount(@PrimaryKey(autoGenerate = true) var id: Long?,
                       @ColumnInfo(name = "site_name") var siteName: String,
                       @ColumnInfo(name = "site_login") var siteLogin: String,
                       @ColumnInfo(name = "site_password") var sitePassword: String,
                       @ColumnInfo(name = "site_description") var siteDescription: String) {
    constructor():this(null,"","","", "")
}