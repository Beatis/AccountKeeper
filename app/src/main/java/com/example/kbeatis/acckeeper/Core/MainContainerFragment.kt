package com.example.kbeatis.acckeeper.Core

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Fragment
import android.arch.lifecycle.*
import android.content.Context
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.Entity.CreditAccount
import com.example.kbeatis.acckeeper.Entity.Note
import com.example.kbeatis.acckeeper.Entity.SiteAccount
import com.example.kbeatis.acckeeper.MainViewModel
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentMainContainerBinding
import com.example.kbeatis.acckeeper.databinding.ItemCreditAccountBinding
import com.example.kbeatis.acckeeper.databinding.ItemNoteAccountBinding
import com.example.kbeatis.acckeeper.databinding.ItemSiteAccountBinding
import com.example.kbeatis.acckeeper.inTransaction
import com.example.kbeatis.acckeeper.inTransactionSupport
import kotlinx.android.synthetic.main.fragment_main_container.view.*

/**
 * Created by kbeatis on 13.03.18.
 */
class MainContainerFragment : BaseFragment() {

    companion object {
        const val SITE_ACCOUNT_PAGE = R.id.site_page
        const val CREDIT_ACCOUNT_PAGE = R.id.credit_page
        const val NOTE_ACCOUNT_PAGE = R.id.note_page
    }

    interface OnFabClickListener {
        fun onFabClick()
    }

    interface OnActionClickListener {
        fun onEditClick()
        fun onDeleteClick()
    }

    private var onFabClickListener: OnFabClickListener? = null
    private var onActionClickListener: OnActionClickListener? = null
    private lateinit var mBinding: FragmentMainContainerBinding
    private lateinit var viewModel: MainViewModel
    private var mAdapter: AccountAdapter? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is OnFabClickListener) {
            throw RuntimeException("You must implement OnFabClickListener")
        }

        if (context !is OnActionClickListener) {
            throw RuntimeException("You must implement OnActionClickListener")
        }

        onFabClickListener = context
        onActionClickListener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_container, container, false)
        invalidateBottomNavigation()
        invalidateFab()
        //TODO спорный observer
        viewModel.getSelectedAccount()?.observe(this, Observer {
            if (it == null) {
                setFragment(SITE_ACCOUNT_PAGE)
            } else {
                setFragment(it)
            }
        })
        return mBinding.root
    }

    fun invalidateBottomNavigation() {
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            setFragment(it.itemId)
            true
        }
        mBinding.bottomNavigationView.setOnNavigationItemReselectedListener {  }
    }

    fun invalidateFab() {
        mBinding.floatingActionButton.setOnClickListener {
            viewModel.getSelectedAccount()?.value = mBinding.bottomNavigationView.selectedItemId
            onFabClickListener?.onFabClick()
        }
    }

    fun invalidateRecycler(layout: Int) {
        mAdapter = AccountAdapter(activity, layout)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        mBinding.recycler.layoutManager = layoutManager
        mBinding.recycler.setHasFixedSize(true)
        when (layout) {
            MainContainerFragment.SITE_ACCOUNT_PAGE -> {
                viewModel.getSites()?.observe(this, Observer<List<SiteAccount>> {
                    if (it != null) {
                        mAdapter?.setSiteList(it)
                    }
                })
            }
            MainContainerFragment.CREDIT_ACCOUNT_PAGE -> {
                viewModel.getCredits()?.observe(this, Observer<List<CreditAccount>> {
                    if (it != null) {
                        mAdapter?.setCreditList(it)
                    }
                })
            }
            MainContainerFragment.NOTE_ACCOUNT_PAGE -> {
                viewModel.getNotes()?.observe(this, Observer<List<Note>> {
                    if (it != null) {
                        mAdapter?.setNoteList(it)
                    }
                })
        }
        }
        mBinding.recycler.adapter = mAdapter
    }

    fun setFragment (menuPage: Int) {
        invalidateRecycler(menuPage)
    }

    internal inner class AccountAdapter(context: Context?, listType: Int) : RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

        private var mListType: Int = 0
        private var mContext: Context?
        private var layoutInflater: LayoutInflater

        private var mSiteList: List<SiteAccount> = ArrayList()
        fun setSiteList(list: List<SiteAccount>) {
            mSiteList = list
            notifyDataSetChanged()
        }
        private var mCreditList: List<CreditAccount> = ArrayList()
        fun setCreditList(list: List<CreditAccount>) {
            mCreditList = list
            notifyDataSetChanged()
        }
        private var mNoteList: List<Note> = ArrayList()
        fun setNoteList(list: List<Note>) {
            mNoteList = list
            notifyDataSetChanged()
        }

        init {
            mListType = listType
            mContext = context
            layoutInflater = LayoutInflater.from(mContext)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            when (mListType) {
                MainContainerFragment.SITE_ACCOUNT_PAGE -> {
                    val mSiteAccountBinding: ItemSiteAccountBinding
                            = DataBindingUtil.inflate(layoutInflater, R.layout.item_site_account, parent, false)
                    val viewHolder = ViewHolder(mSiteAccountBinding)
                    mSiteAccountBinding.root.setOnLongClickListener {
                        createDialog(viewHolder.adapterPosition)
                        true
                    }

                    viewHolder
                }
                MainContainerFragment.CREDIT_ACCOUNT_PAGE -> {
                    val mSiteAccountBinding: ItemCreditAccountBinding
                            = DataBindingUtil.inflate(layoutInflater, R.layout.item_credit_account, parent, false)
                    ViewHolder(mSiteAccountBinding)
                }
                MainContainerFragment.NOTE_ACCOUNT_PAGE -> {
                    val mSiteAccountBinding: ItemNoteAccountBinding
                            = DataBindingUtil.inflate(layoutInflater, R.layout.item_note_account, parent, false)
                    ViewHolder(mSiteAccountBinding)
                }
                else -> {
                    val mSiteAccountBinding: ItemSiteAccountBinding
                            = DataBindingUtil.inflate(layoutInflater, R.layout.item_site_account, parent, false)
                    ViewHolder(mSiteAccountBinding)
                }
            }

        fun createDialog(position: Int) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
            builder.setTitle(R.string.choose_action_title)
                .setItems(R.array.actions, DialogInterface.OnClickListener { dialogInterface, which ->
                    when (which) {
                        0 -> {
                            viewModel.getEditAccount()?.value = when (mListType) {
                                MainContainerFragment.SITE_ACCOUNT_PAGE -> mSiteList.get(position)
                                MainContainerFragment.CREDIT_ACCOUNT_PAGE -> mCreditList.get(position)
                                MainContainerFragment.NOTE_ACCOUNT_PAGE -> mNoteList.get(position)
                                else -> null
                            }
                            onActionClickListener?.onEditClick()
                        }
                        1 -> {
                            viewModel.getDeleteAccount()?.value = when (mListType) {
                                MainContainerFragment.SITE_ACCOUNT_PAGE -> mSiteList.get(position)
                                MainContainerFragment.CREDIT_ACCOUNT_PAGE -> mCreditList.get(position)
                                MainContainerFragment.NOTE_ACCOUNT_PAGE -> mNoteList.get(position)
                                else -> null
                            }
                            onActionClickListener?.onDeleteClick()
                        }
                        else -> return@OnClickListener
                    }
                }).create().show()
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            when (mListType) {
                MainContainerFragment.SITE_ACCOUNT_PAGE -> {
                    val siteAccount = mSiteList.get(position)
                    holder!!.siteAccountBinding.siteAccountName.text = siteAccount.siteName
                    holder.siteAccountBinding.siteAccountLogin.text = siteAccount.siteLogin
                    holder.siteAccountBinding.siteAccountPassword.text = siteAccount.sitePassword
                    if (siteAccount.siteDescription.isEmpty()) {
                        holder.siteAccountBinding.siteAccountDescription.visibility = View.GONE
                    } else {
                        holder.siteAccountBinding.siteAccountDescription.text = siteAccount.siteDescription
                    }
                }
                MainContainerFragment.CREDIT_ACCOUNT_PAGE -> {
                    val creditAccount = mCreditList.get(position)
                    holder!!.creditAccountBinding.creditAccountName.text = creditAccount.creditName
                    holder.creditAccountBinding.creditAccountNumber.text = creditAccount.creditNumber
                    holder.creditAccountBinding.creditAccountExpireDate.text = "${creditAccount.creditExpiredMonth}/${creditAccount.creditExpiredYear}"
                    holder.creditAccountBinding.creditAccountCww.text = creditAccount.creditCWW
                }
                MainContainerFragment.NOTE_ACCOUNT_PAGE -> {
                    val note = mNoteList.get(position)
                    holder!!.noteBinding.noteTitle.text = note.title
                    holder.noteBinding.noteDescription.text = note.description
                }
            }
        }

        override fun getItemCount(): Int =
            when (mListType) {
                MainContainerFragment.SITE_ACCOUNT_PAGE ->  mSiteList.size
                MainContainerFragment.CREDIT_ACCOUNT_PAGE ->  mCreditList.size
                MainContainerFragment.NOTE_ACCOUNT_PAGE ->  mNoteList.size
                else -> 0
            }

        internal inner class ViewHolder : RecyclerView.ViewHolder {
            lateinit var siteAccountBinding: ItemSiteAccountBinding
            lateinit var creditAccountBinding : ItemCreditAccountBinding
            lateinit var noteBinding : ItemNoteAccountBinding

            constructor(mBinding : ItemSiteAccountBinding) : super(mBinding.root) {
                siteAccountBinding = mBinding
            }

            constructor(mBinding : ItemCreditAccountBinding) : super(mBinding.root) {
                creditAccountBinding = mBinding
            }

            constructor(mBinding : ItemNoteAccountBinding) : super(mBinding.root) {
                noteBinding = mBinding
            }
        }
    }
}