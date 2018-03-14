package com.example.kbeatis.acckeeper.Core

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentCreditCardBinding

/**
 * Created by kbeatis on 14.03.18.
 */
class CreditAccountFragment : BaseFragment() {

    private lateinit var mBinding: FragmentCreditCardBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_credit_card, container, false)
        return mBinding.root
    }
}