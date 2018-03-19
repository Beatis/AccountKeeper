package com.example.kbeatis.acckeeper.Create

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kbeatis.acckeeper.AccKeeperDataBase
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.Entity.CreditAccount
import com.example.kbeatis.acckeeper.Entity.Note
import com.example.kbeatis.acckeeper.Entity.SiteAccount
import com.example.kbeatis.acckeeper.MainViewModel
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.ItemEditSiteAccountBinding

/**
 * Created by kbeatis on 15.03.18.
 */
class CreateSiteAccountFragment : BaseFragment() {

    private lateinit var mBinding: ItemEditSiteAccountBinding
    private lateinit var viewModel: MainViewModel
    private var isEditing: Boolean = false
    private var mEditSiteAccount: SiteAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_edit_site_account, container, false)
        invalidateEdit()
        invalidateCreate()
        return mBinding.root
    }

    fun invalidateEdit() = if (viewModel.getEditAccount()?.value != null) {
        isEditing = true
        mEditSiteAccount = viewModel.getEditAccount()?.value as SiteAccount?
        mBinding.siteAccountName.setText(mEditSiteAccount?.siteName)
        mBinding.siteAccountLogin.setText(mEditSiteAccount?.siteLogin)
        mBinding.siteAccountPassword.setText(mEditSiteAccount?.sitePassword)
        mBinding.siteAccountDescription.setText(mEditSiteAccount?.siteDescription)
    } else {
        isEditing = false
    }

    fun invalidateCreate() {
        mBinding.fab.setOnClickListener {
            if (!mBinding.siteAccountName.text.isEmpty() &&
                    !mBinding.siteAccountLogin.text.isEmpty() &&
                    !mBinding.siteAccountPassword.text.isEmpty()) {
                val siteAccount = if (isEditing && mEditSiteAccount != null) mEditSiteAccount else SiteAccount()
                siteAccount?.siteName = mBinding.siteAccountName.text.toString()
                siteAccount?.siteLogin = mBinding.siteAccountLogin.text.toString()
                siteAccount?.sitePassword = mBinding.siteAccountPassword.text.toString()
                siteAccount?.siteDescription = mBinding.siteAccountDescription.text.toString()
                if (isEditing) viewModel.updateSite(siteAccount!!) else viewModel.insertSite(siteAccount!!)
                viewModel.resetEditAccount()
                activity.onBackPressed()
            } else {
                Toast.makeText(activity, R.string.empty_warning, Toast.LENGTH_LONG).show()
            }
        }
    }
}