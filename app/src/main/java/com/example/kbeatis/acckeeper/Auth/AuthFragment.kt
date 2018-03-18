package com.example.kbeatis.acckeeper.Auth

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kbeatis.acckeeper.BaseUtil.BaseFragment
import com.example.kbeatis.acckeeper.Entity.User
import com.example.kbeatis.acckeeper.R
import com.example.kbeatis.acckeeper.databinding.FragmentAuthBinding
import kotlinx.android.synthetic.main.fragment_auth.view.*

/**
 * Created by kbeatis on 13.03.18.
 */
class AuthFragment : BaseFragment() {
    private lateinit var mBinding: FragmentAuthBinding

    interface OnCreateAccountClickListener {
        fun onCreateAccountClick(user: User)
    }

    private lateinit var onCreateAccountClickListener: OnCreateAccountClickListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is OnCreateAccountClickListener)
            throw RuntimeException("OnCreateAccountClickListener must be implemented")
        onCreateAccountClickListener = context

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)
        mBinding.root.create_account_button.setOnClickListener {
            if (!mBinding.loginEditText.text.isEmpty() &&
                    !mBinding.passwordEditText.text.isEmpty() &&
                    !mBinding.secretQuestionEditText.text.isEmpty() &&
                    !mBinding.secretAnswerEditText.text.isEmpty()) {
                val user = User()
                user.userLogin = mBinding.loginEditText.text.toString()
                user.userPassword = mBinding.passwordEditText.text.toString()
                user.secretQuestion = mBinding.secretQuestionEditText.secret_question_edit_text.text.toString()
                user.secretAnswer = mBinding.secretAnswerEditText.text.toString()
                onCreateAccountClickListener.onCreateAccountClick(user)
            } else {
                Toast.makeText(activity, getString(R.string.empty_warning), Toast.LENGTH_LONG).show()
            }
        }
        return mBinding.root
    }
}