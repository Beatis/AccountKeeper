package com.example.kbeatis.acckeeper.Intro

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.kbeatis.acckeeper.AccKeeperDataBase
import com.example.kbeatis.acckeeper.Entity.User

/**
 * Created by kbeatis on 12.03.18.
 */
class IntroViewModel(application: Application) : AndroidViewModel(application) {
    private var mDatabase: AccKeeperDataBase? = null
    private var userList: List<User>? = null

    fun getUsers() : List<User>? = when (userList) {
        null -> {
            userList = mDatabase?.userDao()?.getAll()
            userList
        }
        else -> userList
    }

    fun updateUser(user: User) {
        mDatabase?.userDao()?.update(user)
    }

    fun insertUser(user: User) {
        mDatabase?.userDao()?.insert(user)
    }

    init {
        mDatabase = AccKeeperDataBase.getInstance(getApplication())
    }
}