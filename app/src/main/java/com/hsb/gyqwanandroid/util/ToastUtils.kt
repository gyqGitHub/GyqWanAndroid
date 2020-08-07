package com.hsb.gyqwanandroid.util

import android.app.Application
import android.view.Gravity
import com.hjq.toast.ToastUtils

/**
 *
 * @author gyq
 * @date 2020/8/7
 */

object ToastUtils {

    fun init(application: Application){
        ToastUtils.init(application)
    }

    fun showToast(msg:String,gravity: Int = Gravity.CENTER){
        ToastUtils.setGravity(gravity,0,0)
        ToastUtils.show(msg)
    }
}
