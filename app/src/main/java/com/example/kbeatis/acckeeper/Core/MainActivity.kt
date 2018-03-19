package com.example.kbeatis.acckeeper.Core


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.kbeatis.acckeeper.Auth.AuthActivity
import com.example.kbeatis.acckeeper.BaseUtil.BaseActivity
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.Create.CreateAccountFragment
import com.example.kbeatis.acckeeper.Entity.CreditAccount
import com.example.kbeatis.acckeeper.Entity.Note
import com.example.kbeatis.acckeeper.Entity.SiteAccount
import com.example.kbeatis.acckeeper.Entity.User
import com.example.kbeatis.acckeeper.Intro.IntroActivity
import com.example.kbeatis.acckeeper.MainViewModel
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.ActivityMainBinding
import com.example.kbeatis.acckeeper.inTransactionSupport

class MainActivity : BaseActivity(), MainContainerFragment.OnFabClickListener,
        MainContainerFragment.OnActionClickListener{

    override fun onEditClick() {
        replaceWithAddBackstackTransaction(CreateAccountFragment())
    }

    override fun onDeleteClick() {
        when (viewModel.getDeleteAccount()?.value) {
            is SiteAccount -> viewModel.getDatabase()?.siteAccountDao()
                    ?.delete(viewModel.getDeleteAccount()?.value as SiteAccount)
            is CreditAccount -> viewModel.getDatabase()?.creditAccountDao()
                    ?.delete(viewModel.getDeleteAccount()?.value as CreditAccount)
            is Note -> viewModel.getDatabase()?.noteDao()
                    ?.delete(viewModel.getDeleteAccount()?.value as Note)
        }
    }

    override fun onFabClick() {
        replaceWithAddBackstackTransaction(CreateAccountFragment())
    }

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        replaceTransaction(MainContainerFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.logout -> {
            val userList = viewModel.getDatabase()?.userDao()?.getAll()
            if(userList != null) {
                val currentUser = userList.get(0)
                currentUser.isAuthorized = false
                viewModel.getDatabase()?.userDao()?.update(currentUser)
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }
            true
        }
        R.id.exit -> {
            viewModel.wipeAllData()
            startActivity(Intent(this, IntroActivity()::class.java))
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun replaceTransaction (fragment: BaseFragment) {
        supportFragmentManager.inTransactionSupport {
            replace(mBinding.mainContainer.id, fragment)
        }
    }

    fun replaceWithAddBackstackTransaction (fragment: BaseFragment) {
        supportFragmentManager.inTransactionSupport {
            addToBackStack("OnCreateAccountFragment")
            replace(mBinding.mainContainer.id, fragment)
        }
    }
}
