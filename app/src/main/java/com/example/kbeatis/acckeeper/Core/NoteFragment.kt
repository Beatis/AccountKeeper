package com.example.kbeatis.acckeeper.Core

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentNoteBinding

/**
 * Created by kbeatis on 14.03.18.
 */
class NoteFragment : BaseFragment() {

    private lateinit var mBinding: FragmentNoteBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)
        return mBinding.root
    }
}