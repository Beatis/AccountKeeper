package com.example.kbeatis.acckeeper.Core

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentMainContainerBinding
import com.example.kbeatis.acckeeper.inTransaction
import kotlinx.android.synthetic.main.fragment_main_container.view.*

/**
 * Created by kbeatis on 13.03.18.
 */
class MainContainerFragment : BaseFragment() {

    companion object {
        private val SITE_ACCOUNT_PAGE = R.id.site_page
        private val CREDIT_ACCOUNT_PAGE = R.id.credit_page
        private val NOTE_ACCOUNT_PAGE = R.id.note_page
    }

    private lateinit var mBinding: FragmentMainContainerBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_container, container, false)
        invalidateBottomNavigation()
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