package com.hsb.gyqwanandroid.ui.main

import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment
import com.hsb.gyqwanandroid.data.HomeRepository
import com.hsb.gyqwanandroid.data.model.Banner
import com.hsb.gyqwanandroid.ui.login.LoginResult
import com.hsb.gyqwanandroid.util.ToastUtils
import com.hsb.gyqwanandroid.util.extension.request

/**
 * @author gyq
 * @date 2020/7/18
 */
class MainViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val fragments by lazy {
        SparseArray<BaseFragment>().apply {
            put(R.id.menu_main,HomeFragment.newInstance())
            put(R.id.menu_project,ProjectFragment.newInstance())
            put(R.id.menu_square,SquareFragment.newInstance())
            put(R.id.menu_public,PublicFragment.newInstance())
            put(R.id.menu_me,MineFragment.newInstance())
        }
    }

    private val _bannerList = MutableLiveData<List<Banner>>()
    val bannerList: LiveData<List<Banner>> = _bannerList

    fun getBannerList(){
        request(onRequest = {
            homeRepository.getBannerList()
        },onSuccess = {
            _bannerList.value = it
        },onError = {
            ToastUtils.showToast(it)
        })
    }

}


