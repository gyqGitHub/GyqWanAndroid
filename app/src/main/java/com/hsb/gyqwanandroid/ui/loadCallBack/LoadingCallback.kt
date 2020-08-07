package com.hsb.gyqwanandroid.ui.loadCallBack

import android.content.Context
import android.view.View
import com.hsb.gyqwanandroid.R
import com.kingja.loadsir.callback.Callback

/**
 * Description:TODO
 * Create Time:2017/9/4 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading_state
    }

    override fun getSuccessVisible(): Boolean {
        return super.getSuccessVisible()
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}
