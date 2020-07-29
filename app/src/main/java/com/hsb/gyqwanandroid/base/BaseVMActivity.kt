package com.hsb.gyqwanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author gyq
 * @date 2020/7/29
 */
abstract class BaseVMActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        startObserve()
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
    abstract fun layoutId():Int
}