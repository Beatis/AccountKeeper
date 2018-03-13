package com.example.kbeatis.acckeeper.Intro

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.kbeatis.acckeeper.AccKeeperDataBase
import com.example.kbeatis.acckeeper.Entity.User

/**
 * Created by kbeatis on 12.03.18.
 */
class IntroActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var mDatabase: AccKeeperDataBase? = null
    private var livedataUsers: LiveData<List<User>>? = null

    fun getUsers() : LiveData<List<User>>? {
        if (livedataUsers == null) {
            livedataUsers = mDatabase?.userDao()?.getAll()
        }
        return livedataUsers
    }

    init {
        mDatabase = AccKeeperDataBase.getInstance(getApplication())
    }
}