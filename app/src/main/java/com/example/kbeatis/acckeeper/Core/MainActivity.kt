package com.example.kbeatis.acckeeper.Core

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.kbeatis.acckeeper.BaseUtil.BaseActivity
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.ActivityMainBinding
import com.example.kbeatis.acckeeper.Auth.AuthActivity
import com.example.kbeatis.acckeeper.inTransaction

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        startTransaction()
    }

    fun startTransaction () {
        fragmentManager.inTransaction{
            replace(mBinding.mainContainer.id, MainContainerFragment())
        }
    }
}
