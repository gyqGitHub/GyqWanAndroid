package com.hsb.gyqwanandroid.util.extension

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hsb.gyqwanandroid.util.ToastUtils

/**
 * AppCompatActivity一系列扩展方法
 * @author gyq
 * @date 2020/7/28
 */
inline fun AppCompatActivity.getColorIntFromRes(@ColorRes resId: Int): Int {
    return resources.getColor(resId)
}

inline fun <reified T:AppCompatActivity>AppCompatActivity.start(extras:Bundle? = null){
    startActivity(Intent(this,T::class.java).apply {
        if (extras != null) putExtras(extras)
    })
}

fun AppCompatActivity.showToast(msg:String, gravity: Int = Gravity.CENTER){
    ToastUtils.showToast(msg,gravity)
}

inline fun <reified T : ViewModel> AppCompatActivity.obtainViewModel(viewModelFactory: ViewModelProvider.Factory) =
    ViewModelProviders.of(this, viewModelFactory).get(T::class.java)

fun AppCompatActivity.log(msg:Any?,tag:String = "gyq"){
    Log.e(tag,msg.toString())
}