package com.example.kbeatis.acckeeper.Create

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentCreateAccountBinding
import com.example.kbeatis.acckeeper.databinding.ItemEditCreditAccountBinding

/**
 * Created by kbeatis on 15.03.18.
 */
class CreateCreditAccountFragment : BaseFragment() {

    private lateinit var mBinding: ItemEditCreditAccountBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_edit_credit_account, container, false)
        return mBinding.root
    }
}