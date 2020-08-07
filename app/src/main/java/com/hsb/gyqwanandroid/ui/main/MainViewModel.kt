package com.hsb.gyqwanandroid.ui.main

import android.util.SparseArray
import androidx.lifecycle.ViewModel
import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment

/**
 * @author gyq
 * @date 2020/7/18
 */
class MainViewModel : ViewModel() {

    val fragments by lazy {
        SparseArray<BaseFragment>().apply {
            put(R.id.menu_main,HomeFragment.newInstance())
            put(R.id.menu_project,ProjectFragment.newInstance())
            put(R.id.menu_square,SquareFragment.newInstance())
            put(R.id.menu_public,PublicFragment.newInstance())
            put(R.id.menu_me,MineFragment.newInstance())
        }
    }


}


