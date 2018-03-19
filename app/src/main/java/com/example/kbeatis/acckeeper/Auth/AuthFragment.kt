package com.example.kbeatis.acckeeper.Auth

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.Entity.User
import com.example.kbeatis.acckeeper.Intro.IntroViewModel
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentAuthBinding
import kotlinx.android.synthetic.main.fragment_auth.view.*

/**
 * Created by kbeatis on 13.03.18.
 */
class AuthFragment : BaseFragment() {
    private lateinit var mBinding: FragmentAuthBinding

    interface OnCreateAccountClickListener {
        fun onLoginAccountClick()
        fun onCreateAccountClick()
    }

    private lateinit var onCreateAccountClickListener: OnCreateAccountClickListener
    private lateinit var viewModel: IntroViewModel
    private var mCurrentUser: User? = null
    private var isCurrentUser: Boolean = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is OnCreateAccountClickListener)
            throw RuntimeException("OnCreateAccountClickListener must be implemented")
        onCreateAccountClickListener = context

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(IntroViewModel::class.java)
        val userList = viewModel.getUsers()
        if (userList != null && !userList.isEmpty()) {
            val currentUser = userList.get(0)
            if (!currentUser.isAuthorized) {
                mCurrentUser = currentUser
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)
        invalidateCurrentUser()
        invalidateCreateButton()
        return mBinding.root
    }

    fun invalidateCurrentUser() {
        if (mCurrentUser != null) {
            isCurrentUser = true
            mBinding.titleAuthReference.setText(getString(R.string.enter_password_title) + "${mCurrentUser?.userLogin}")
            mBinding.inputLogin.visibility = View.GONE
            mBinding.inputQuestion.visibility = View.GONE
            mBinding.inputAnswer.visibility = View.GONE
            mBinding.createAccountButton.setText(getString(R.string.login_title))
        }
    }

    fun invalidateCreateButton() {
        mBinding.createAccountButton.setOnClickListener {
            if (isCurrentUser) {
                if (mCurrentUser?.userPassword == mBinding.passwordEditText.text.toString()) {
                    mCurrentUser?.isAuthorized = true
                    viewModel.updateUser(mCurrentUser!!)
                    onCreateAccountClickListener.onLoginAccountClick()
                }
            } else {
                if (!mBinding.loginEditText.text.isEmpty() &&
                        !mBinding.passwordEditText.text.isEmpty() &&
                        !mBinding.secretQuestionEditText.text.isEmpty() &&
                        !mBinding.secretAnswerEditText.text.isEmpty()) {
                    val user = User()
                    user.userLogin = mBinding.loginEditText.text.toString()
                    user.userPassword = mBinding.passwordEditText.text.toString()
                    user.secretQuestion = mBinding.secretQuestionEditText.secret_question_edit_text.text.toString()
                    user.secretAnswer = mBinding.secretAnswerEditText.text.toString()
                    viewModel.insertUser(user)
                    onCreateAccountClickListener.onCreateAccountClick()
                } else {
                    Toast.makeText(activity, getString(R.string.empty_warning), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}