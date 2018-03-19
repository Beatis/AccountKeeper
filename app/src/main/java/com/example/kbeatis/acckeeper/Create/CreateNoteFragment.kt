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

    private var isEditing: Boolean = false
    private var mEditNoteAccount: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_edit_note, container, false)
        invalidateEdit()
        invalidateCreate()
        return mBinding.root
    }

    fun invalidateEdit() = if (viewModel.getEditAccount()?.value != null) {
        isEditing = true
        mEditNoteAccount = viewModel.getEditAccount()?.value as Note?
        mBinding.noteTitle.setText(mEditNoteAccount?.title)
        mBinding.noteDescription.setText(mEditNoteAccount?.description)
    } else {
        isEditing = false
    }


    fun invalidateCreate() {
        mBinding.fab.setOnClickListener {
            if(!mBinding.noteTitle.text.isEmpty()
                    && !mBinding.noteDescription.text.isEmpty()) {
                val note = if (isEditing && mEditNoteAccount != null) mEditNoteAccount else Note()
                note?.title = mBinding.noteTitle.text.toString()
                note?.description = mBinding.noteDescription.text.toString()
                if (isEditing) viewModel.updateNote(note!!) else viewModel.insertNote(note!!)
                viewModel.resetEditAccount()
                activity.onBackPressed()
            } else {
                Toast.makeText(activity, R.string.empty_warning, Toast.LENGTH_LONG).show()
            }
        }
    }
}