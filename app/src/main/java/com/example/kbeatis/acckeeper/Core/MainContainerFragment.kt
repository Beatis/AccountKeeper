package com.example.kbeatis.acckeeper.Core

import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.MainViewModel
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentMainContainerBinding
import com.example.kbeatis.acckeeper.inTransaction
import kotlinx.android.synthetic.main.fragment_main_container.view.*

/**
 * Created by kbeatis on 13.03.18.
 */
class MainContainerFragment : BaseFragment() {

    companion object {
        public const val SITE_ACCOUNT_PAGE = R.id.site_page
        public const val CREDIT_ACCOUNT_PAGE = R.id.credit_page
        public const val NOTE_ACCOUNT_PAGE = R.id.note_page
    }

    public interface OnFabClickListener {
        fun onFabClick()
    }

    private lateinit var onFabClickListener: OnFabClickListener
    private lateinit var mBinding: FragmentMainContainerBinding
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is OnFabClickListener) {
            throw RuntimeException("You must implement OnFabClickListener")
        }
        onFabClickListener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_container, container, false)
        invalidateBottomNavigation()
        invalidateFab()
        if (savedInstanceState == null) {
            setFragment(SITE_ACCOUNT_PAGE)
        }
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
            viewModel.selectedAccount.postValue(NOTE_ACCOUNT_PAGE)
            onFabClickListener.onFabClick()
        }
    }

    fun setFragment (menuItem: Int) {
        val fragment: Fragment? = when (menuItem) {
            SITE_ACCOUNT_PAGE -> SiteAccountFragment()
            CREDIT_ACCOUNT_PAGE -> CreditAccountFragment()
            NOTE_ACCOUNT_PAGE -> NoteFragment()
            else -> null
        }
        childFragmentManager.inTransaction {
            replace(mBinding.fragmentMainContainer.id, fragment)
        }
    }
}