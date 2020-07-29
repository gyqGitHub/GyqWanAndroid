package com.hsb.gyqwanandroid.util.extension

import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * AppCompatActivity一系列扩展方法
 * @author gyq
 * @date 2020/7/28
 */
inline fun AppCompatActivity.getColorIntFromRes(@ColorRes resId: Int): Int {
    return resources.getColor(resId)
}

inline fun <reified T : ViewModel> AppCompatActivity.obtainViewModel(viewModelFactory: ViewModelProvider.Factory) =
    ViewModelProviders.of(this, viewModelFactory).get(T::class.java)

