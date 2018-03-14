package com.example.kbeatis.acckeeper.Auth

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.kbeatis.acckeeper.AccKeeperDataBase
import com.example.kbeatis.acckeeper.BaseUtil.BaseActivity
import com.example.kbeatis.acckeeper.Core.MainActivity
import com.example.kbeatis.acckeeper.Entity.User
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.ActivityAuthBinding
import com.example.kbeatis.acckeeper.databinding.ActivityMainBinding
import com.example.kbeatis.acckeeper.inTransaction

/**
 * Created by kbeatis on 10.03.18.
 */
class AuthActivity : BaseActivity(), AuthFragment.OnCreateAccountClickListener {
    override fun onCreateAccountClick(user: User) {
        AccKeeperDataBase.getInstance(applicationContext)?.userDao()?.insert(user)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    private lateinit var mBinding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        startTransaction()
    }

    private fun startTransaction() {
        fragmentManager.inTransaction {
            replace(mBinding.authContainer.id, AuthFragment())
        }
    }
}