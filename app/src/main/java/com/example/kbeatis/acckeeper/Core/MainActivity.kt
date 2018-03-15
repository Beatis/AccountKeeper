package com.example.kbeatis.acckeeper.Core

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.kbeatis.acckeeper.BaseUtil.BaseActivity
import com.example.kbeatis.acckeeper.Create.CreateAccountFragment
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.ActivityMainBinding
import com.example.kbeatis.acckeeper.inTransaction

class MainActivity : BaseActivity(), MainContainerFragment.OnFabClickListener {
    override fun onFabClick() {
        fragmentManager.inTransaction {
            replace(mBinding.mainContainer.id, CreateAccountFragment())
            addToBackStack("CreateAccount")
        }
    }

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        replaceTransaction()
    }

    fun replaceTransaction () {
        fragmentManager.inTransaction{
            replace(mBinding.mainContainer.id, MainContainerFragment())
        }
    }
}
