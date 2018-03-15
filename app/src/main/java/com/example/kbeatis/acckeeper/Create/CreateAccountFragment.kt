package com.example.kbeatis.acckeeper.Create

import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.Core.MainContainerFragment
import com.example.kbeatis.acckeeper.MainViewModel
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentCreateAccountBinding
import com.example.kbeatis.acckeeper.inTransaction

/**
 * Created by kbeatis on 15.03.18.
 */
class CreateAccountFragment : BaseFragment() {

    private lateinit var mBinding: FragmentCreateAccountBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_account, container, false)
        checkSelectedPage(viewModel.selectedAccount.value!!)
        return mBinding.root
    }

    fun checkSelectedPage(page: Int) {
        when (page) {
            MainContainerFragment.SITE_ACCOUNT_PAGE -> replaceTransaction(CreateSiteAccountFragment())
            MainContainerFragment.CREDIT_ACCOUNT_PAGE -> replaceTransaction(CreateCreditAccountFragment())
            MainContainerFragment.NOTE_ACCOUNT_PAGE -> replaceTransaction(CreateNoteFragment())
            else -> return
        }
    }

    fun replaceTransaction(fragment: Fragment) {
        childFragmentManager.inTransaction {
            replace(mBinding.createAccountContainer.id, fragment)
        }
    }
}