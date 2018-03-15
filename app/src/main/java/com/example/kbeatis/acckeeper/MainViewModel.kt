package com.example.kbeatis.acckeeper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

/**
 * Created by kbeatis on 15.03.18.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var mDataBase: AccKeeperDataBase? = null
    var selectedAccount: MutableLiveData<Int> = MutableLiveData()

    init {
        mDataBase = AccKeeperDataBase.getInstance(getApplication())
    }
}