package com.hsb.gyqwanandroid.ui.main

import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment

class SquareFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_square

    companion object {
        @JvmStatic
        fun newInstance() = SquareFragment()
    }
}