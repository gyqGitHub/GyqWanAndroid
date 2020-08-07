package com.hsb.gyqwanandroid.ui.main

import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment

class MineFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_mine

    companion object {
        @JvmStatic
        fun newInstance() = MineFragment()
    }
}