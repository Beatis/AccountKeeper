package com.example.kbeatis.acckeeper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.TextView
import com.example.kbeatis.acckeeper.Entity.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var newUser: User = User()
        var mTextView: TextView = findViewById(R.id.text_view)
        mTextView.text = "Some Shit happens"
        mTextView.text = newUser.userLogin
        newUser.userLogin = "You are in Main"
        mTextView.text = newUser.userLogin
    }
}
