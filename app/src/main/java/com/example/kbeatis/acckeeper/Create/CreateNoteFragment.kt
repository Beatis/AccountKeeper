package com.example.kbeatis.acckeeper.Create

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.Entity.Note
import com.example.kbeatis.acckeeper.MainViewModel
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.ItemEditNoteBinding

/**
 * Created by kbeatis on 15.03.18.
 */
class CreateNoteFragment : BaseFragment() {

    private lateinit var mBinding: ItemEditNoteBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_edit_note, container, false)
        invalidateCreate()
        return mBinding.root
    }

    fun invalidateCreate() {
        mBinding.fab.setOnClickListener {
            if(!mBinding.noteTitle.text.isEmpty()
                    && !mBinding.noteDescription.text.isEmpty()) {
                val note = Note()
                note.title = mBinding.noteTitle.text.toString()
                note.description = mBinding.noteDescription.text.toString()
                viewModel.insertNote(note)
                activity.onBackPressed()
            } else {
                Toast.makeText(activity, R.string.empty_warning, Toast.LENGTH_LONG).show()
            }
        }
    }
}