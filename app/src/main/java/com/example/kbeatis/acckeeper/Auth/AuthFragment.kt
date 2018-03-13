package com.example.kbeatis.acckeeper.Auth

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentAuthBinding

/**
 * Created by kbeatis on 13.03.18.
 */
class AuthFragment : BaseFragment() {

    private lateinit var mBinding: FragmentAuthBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)
        return mBinding.root
    }
}