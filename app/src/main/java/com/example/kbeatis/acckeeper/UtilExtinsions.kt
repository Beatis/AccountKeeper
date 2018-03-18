package com.example.kbeatis.acckeeper

import android.app.FragmentManager
import android.app.FragmentTransaction

/**
 * Created by kbeatis on 13.03.18.
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

inline fun android.support.v4.app.FragmentManager
        .inTransactionSupport(func: android.support.v4.app.FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}