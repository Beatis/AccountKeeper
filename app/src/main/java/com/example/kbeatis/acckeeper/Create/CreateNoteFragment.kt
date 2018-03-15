package com.example.kbeatis.acckeeper.Create

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.ItemEditNoteBinding

/**
 * Created by kbeatis on 15.03.18.
 */
class CreateNoteFragment : BaseFragment() {

    private lateinit var mBinding: ItemEditNoteBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_edit_note, container, false)
        return mBinding.root
    }
}