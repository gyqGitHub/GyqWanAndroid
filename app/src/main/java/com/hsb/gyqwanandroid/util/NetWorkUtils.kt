package com.hsb.gyqwanandroid.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * 网络是否可用
 */
fun isNetworkAvailable(context: Context): Boolean {
    val manager = context.applicationContext.getSystemService(
        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = manager.activeNetworkInfo
    return info?.isAvailable == true
}