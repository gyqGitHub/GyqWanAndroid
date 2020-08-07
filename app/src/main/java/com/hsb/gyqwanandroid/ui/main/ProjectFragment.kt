package com.hsb.gyqwanandroid.ui.main

import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment

class ProjectFragment : BaseFragment() {

    override fun getLayoutId(): Int  = R.layout.fragment_project


    companion object {
        @JvmStatic
        fun newInstance() = ProjectFragment()
    }
}