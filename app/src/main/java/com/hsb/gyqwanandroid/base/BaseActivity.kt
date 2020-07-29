package com.hsb.gyqwanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author gyq
 * @date 2020/7/29
 */
abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        initData()
    }

    open fun initView(){

    }

    open fun initData(){

    }

    abstract fun layoutId():Int
}