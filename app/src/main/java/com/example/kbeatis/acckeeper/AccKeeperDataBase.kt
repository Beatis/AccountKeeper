package com.example.kbeatis.acckeeper

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.kbeatis.acckeeper.DAO.CreaditAccountDao
import com.example.kbeatis.acckeeper.DAO.NoteDao
import com.example.kbeatis.acckeeper.DAO.SiteAccountDao
import com.example.kbeatis.acckeeper.DAO.UserDao
import com.example.kbeatis.acckeeper.Entity.CreditAccount
import com.example.kbeatis.acckeeper.Entity.Note
import com.example.kbeatis.acckeeper.Entity.SiteAccount
import com.example.kbeatis.acckeeper.Entity.User

/**
 * Created by kbeatis on 07.03.18.
 */
@Database(entities = arrayOf(User::class, SiteAccount::class, CreditAccount::class, Note::class), version = 1)
abstract class AccKeeperDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun creditAccountDao() : CreaditAccountDao
    abstract fun siteAccountDao() : SiteAccountDao
    abstract fun noteDao() : NoteDao

    companion object {
        private var INSTANCE: AccKeeperDataBase? = null

        fun getInstance(context: Context): AccKeeperDataBase? {
            if (INSTANCE == null) {
                synchronized(AccKeeperDataBase::class) {
                    //Не шути с allowMainThreadQueries Вася, надо будет переделать под другой поток
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AccKeeperDataBase::class.java, "acc_keeper.db")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}