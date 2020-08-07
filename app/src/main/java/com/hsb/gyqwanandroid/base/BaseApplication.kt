package com.hsb.gyqwanandroid.base

import android.app.Application
import com.hsb.gyqwanandroid.util.ToastUtils

/**
 *
 * @author gyq
 * @date 2020/8/7
 */
class BaseApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        ToastUtils.init(this)
    }
}