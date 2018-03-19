package com.example.kbeatis.acckeeper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.kbeatis.acckeeper.Core.MainContainerFragment
import com.example.kbeatis.acckeeper.Entity.CreditAccount
import com.example.kbeatis.acckeeper.Entity.Note
import com.example.kbeatis.acckeeper.Entity.SiteAccount

/**
 * Created by kbeatis on 15.03.18.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var mDatabase: AccKeeperDataBase? = null
    private var selectedAccount: MutableLiveData<Int>? = null

    private var livedataSiteAccount: LiveData<List<SiteAccount>>? = null
    private var livedataCreditAccount: LiveData<List<CreditAccount>>? = null
    private var livedataNote: LiveData<List<Note>>? = null

    private var livedataEditAccount: MutableLiveData<Any>? = null
    private var livedataDeleteAccount: MutableLiveData<Any>? = null

    fun getSelectedAccount() : MutableLiveData<Int>? {
        if(selectedAccount == null) {
            selectedAccount = MutableLiveData<Int>()
            selectedAccount?.value = MainContainerFragment.SITE_ACCOUNT_PAGE
        }
        return selectedAccount
    }

    fun getEditAccount() : MutableLiveData<Any>? = when (livedataEditAccount) {
        null -> {
            livedataEditAccount = MutableLiveData()
            livedataEditAccount
        }
        else -> livedataEditAccount
    }

    fun resetEditAccount() {
        livedataEditAccount = null
    }

    fun getDeleteAccount() : MutableLiveData<Any>? = when (livedataDeleteAccount) {
        null -> {
            livedataDeleteAccount = MutableLiveData()
            livedataDeleteAccount
        }
        else -> livedataDeleteAccount
    }


    fun getSites() : LiveData<List<SiteAccount>>? =  when (livedataSiteAccount) {
        null -> {
            livedataSiteAccount = mDatabase?.siteAccountDao()?.getAll()
            livedataSiteAccount
        }
        else -> livedataSiteAccount
    }

    fun getCredits() : LiveData<List<CreditAccount>>? =  when (livedataCreditAccount) {
        null -> {
            livedataCreditAccount = mDatabase?.creditAccountDao()?.getAll()
            livedataCreditAccount
        }
        else -> livedataCreditAccount
    }

    fun getNotes() : LiveData<List<Note>>? =  when (livedataNote) {
        null -> {
            livedataNote = mDatabase?.noteDao()?.getAll()
            livedataNote
        }
        else -> livedataNote
    }

    fun insertSite(siteAccount: SiteAccount) {
        mDatabase?.siteAccountDao()?.insert(siteAccount)
    }

    fun insertCredit(creditAccount: CreditAccount) {
        mDatabase?.creditAccountDao()?.insert(creditAccount)
    }

    fun insertNote(note: Note) {
        mDatabase?.noteDao()?.insert(note)
    }

    fun updateSite(siteAccount: SiteAccount) {
        mDatabase?.siteAccountDao()?.update(siteAccount)
    }

    fun updateCredit(creditAccount: CreditAccount) {
        mDatabase?.creditAccountDao()?.update(creditAccount)
    }

    fun updateNote(note: Note) {
        mDatabase?.noteDao()?.update(note)
    }

    init {
        mDatabase = AccKeeperDataBase.getInstance(getApplication())
    }

    fun getDatabase() : AccKeeperDataBase? = mDatabase

    fun wipeAllData() {
        mDatabase?.siteAccountDao()?.deleteAll()
        mDatabase?.creditAccountDao()?.deleteAll()
        mDatabase?.noteDao()?.deleteAll()
        mDatabase?.userDao()?.deleteAll()
    }
}