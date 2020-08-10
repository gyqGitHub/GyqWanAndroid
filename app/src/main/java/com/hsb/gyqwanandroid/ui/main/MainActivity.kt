package com.hsb.gyqwanandroid.ui.main

import androidx.activity.viewModels
import androidx.core.util.isEmpty
import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseActivity
import com.hsb.gyqwanandroid.util.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory()
    }

    override fun initView() {
        super.initView()
        switchFragment(-1, R.id.menu_main)
        bottom_navigate.setOnNavigationItemSelectedListener {
            switchFragment(bottom_navigate.selectedItemId, it.itemId)
            return@setOnNavigationItemSelectedListener true
        }
    }


    private fun switchFragment(selectedId: Int, menuId: Int) {
        if (mainViewModel.fragments.isEmpty()) {
            return
        }
        val preFragment = mainViewModel.fragments[selectedId]
        val destFragment = mainViewModel.fragments.get(menuId) ?: return

        supportFragmentManager.beginTransaction().run {
            if (preFragment != null && preFragment.isAdded) {
                hide(preFragment)
            }
            if (destFragment.isAdded) {
                show(destFragment).commit()
            } else {
                add(R.id.fragment_container, destFragment).commit()
            }
        }
    }

    override fun layoutId(): Int = R.layout.activity_main

}
