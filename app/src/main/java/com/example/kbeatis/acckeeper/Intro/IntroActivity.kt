package com.example.kbeatis.acckeeper.Intro

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.example.kbeatis.acckeeper.AccKeeperDataBase
import com.example.kbeatis.acckeeper.Auth.AuthActivity
import com.example.kbeatis.acckeeper.BaseUtil.BaseActivity
import com.example.kbeatis.acckeeper.Core.MainActivity
import com.example.kbeatis.acckeeper.Entity.User

/**
 * Created by kbeatis on 07.03.18.
 */
class IntroActivity : BaseActivity() {

    private lateinit var mDataBase: AccKeeperDataBase
    private lateinit var viewModel: IntroActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*TODO проверить зарегистрирован ли уже аккаунт
        если да, то кидать на главный экран
        если нет, то кидать на экран с регистрацией
         */

        viewModel = ViewModelProviders.of(this).get(IntroActivityViewModel::class.java)
        viewModel.getUsers()?.observe(this, Observer<List<User>> {
            if (it != null) {
                if (!it.isEmpty()) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, AuthActivity::class.java))
                }
                finish()
            }
        })
//        Single.fromCallable {
//                val dataBase = AccKeeperDataBase.getInstance(this)
//                val userDao = dataBase?.userDao()
//                userDao?.insert(User())
//                startActivity(checkRegistredUsers())
//            }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe()

    }
}