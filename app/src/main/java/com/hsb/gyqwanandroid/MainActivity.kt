package com.hsb.gyqwanandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hsb.gyqwanandroid.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
//            mainViewModel.userNeedsDocs()
        } catch (e: Exception) {
        }

        btn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }


}
