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
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.Entity.CreditAccount
import com.example.kbeatis.acckeeper.Entity.SiteAccount
import com.example.kbeatis.acckeeper.MainViewModel
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.ItemEditCreditAccountBinding

/**
 * Created by kbeatis on 15.03.18.
 */
class CreateCreditAccountFragment : BaseFragment() {

    private lateinit var mBinding: ItemEditCreditAccountBinding
    private lateinit var viewModel: MainViewModel

    private var isEditing: Boolean = false
    private var mEditCreditAccount: CreditAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_edit_credit_account, container, false)
        invalidateEdit()
        invalidateCreate()
        return mBinding.root
    }

    fun invalidateEdit() = if (viewModel.getEditAccount()?.value != null) {
        isEditing = true
        mEditCreditAccount = viewModel.getEditAccount()?.value as CreditAccount?
        mBinding.creditAccountName.setText(mEditCreditAccount?.creditName)
        mBinding.creditAccountNumber.setText(mEditCreditAccount?.creditNumber)
        mBinding.creditAccountMonth.setText(mEditCreditAccount?.creditExpiredMonth)
        mBinding.creditAccountYear.setText(mEditCreditAccount?.creditExpiredYear)
        mBinding.creditAccountCww.setText(mEditCreditAccount?.creditCWW)
    } else {
        isEditing = false
    }

    fun invalidateCreate() {
        mBinding.fab.setOnClickListener {
            if(!mBinding.creditAccountName.text.isEmpty()
                    && !mBinding.creditAccountNumber.text.isEmpty()
                    && !mBinding.creditAccountMonth.text.isEmpty()
                    && !mBinding.creditAccountYear.text.isEmpty()
                    && !mBinding.creditAccountCww.text.isEmpty()) {
                val creditAccount = if (isEditing && mEditCreditAccount != null) mEditCreditAccount else CreditAccount()
                creditAccount?.creditName = mBinding.creditAccountName.text.toString()
                creditAccount?.creditNumber = mBinding.creditAccountNumber.text.toString()
                creditAccount?.creditExpiredMonth = mBinding.creditAccountMonth.text.toString()
                creditAccount?.creditExpiredYear = mBinding.creditAccountYear.text.toString()
                creditAccount?.creditCWW = mBinding.creditAccountCww.text.toString()
                if (isEditing) viewModel.updateCredit(creditAccount!!) else viewModel.insertCredit(creditAccount!!)
                viewModel.resetEditAccount()
                activity.onBackPressed()
            } else {
                Toast.makeText(activity, R.string.empty_warning, Toast.LENGTH_LONG).show()
            }
        }
    }
}