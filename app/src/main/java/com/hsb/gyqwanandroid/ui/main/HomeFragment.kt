package com.hsb.gyqwanandroid.ui.main

import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment

class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}