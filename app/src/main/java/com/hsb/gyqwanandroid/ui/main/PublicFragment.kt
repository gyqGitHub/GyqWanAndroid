package com.hsb.gyqwanandroid.ui.main

import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment

class PublicFragment : BaseFragment() {

    override fun getLayoutId(): Int  = R.layout.fragment_public

    companion object {

        @JvmStatic
        fun newInstance() = PublicFragment()
    }
}