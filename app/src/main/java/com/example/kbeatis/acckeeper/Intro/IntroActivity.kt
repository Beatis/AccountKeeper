package com.example.kbeatis.acckeeper.Intro

import android.content.Intent
import android.os.Bundle
import com.example.kbeatis.acckeeper.AccKeeperDataBase
import com.example.kbeatis.acckeeper.Auth.AuthActivity
import com.example.kbeatis.acckeeper.BaseActivity
import com.example.kbeatis.acckeeper.MainActivity

/**
 * Created by kbeatis on 07.03.18.
 */
class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*TODO проверить зарегистрирован ли уже аккаунт
        если да, то кидать на главный экран
        если нет, то кидать на экран с регистрацией
         */
        startActivity(checkRegistredUsers())
    }

    fun checkRegistredUsers() : Boolean {
        val dataBase = AccKeeperDataBase.getInstance(this)
        dataBase?.userDao()?.getAll() ?: return false
        return true
    }

    fun startActivity(isAccountRegistred: Boolean) {
        if (isAccountRegistred) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, AuthActivity::class.java))
        }
        finish()
    }
}