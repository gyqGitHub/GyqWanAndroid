package com.hsb.gyqwanandroid.ui.main

import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment
import com.hsb.gyqwanandroid.data.HomeRepository
import com.hsb.gyqwanandroid.data.model.Article
import com.hsb.gyqwanandroid.data.model.BannerData
import com.hsb.gyqwanandroid.data.model.PageResponse
import com.hsb.gyqwanandroid.util.ToastUtils
import com.hsb.gyqwanandroid.util.extension.request

/**
 * @author gyq
 * @date 2020/7/18
 */
class MainViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    lateinit var articleAdapter: BaseQuickAdapter<Article, BaseViewHolder>

    val fragments by lazy {
        SparseArray<BaseFragment>().apply {
            put(R.id.menu_main,HomeFragment.newInstance())
            put(R.id.menu_project,ProjectFragment.newInstance())
            put(R.id.menu_square,SquareFragment.newInstance())
            put(R.id.menu_public,PublicFragment.newInstance())
            put(R.id.menu_me,MineFragment.newInstance())
        }
    }

    private val _bannerList = MutableLiveData<List<BannerData>>()
    val bannerDataList: LiveData<List<BannerData>> = _bannerList

    private val _articlePage = MutableLiveData<PageResponse<Article>>()
    val articlePage:LiveData<PageResponse<Article>> = _articlePage

    fun getBannerList(){
        request(onRequest = {
            homeRepository.getBannerList()
        },onSuccess = {
            _bannerList.value = it
        },onError = {
            ToastUtils.showToast(it)
        })
    }

    fun getArticleList(isRefresh:Boolean){
        request(onRequest = {
            homeRepository.getArticleList(if(isRefresh) 0 else _articlePage.value?.curPage ?: -1 + 1)
        },onSuccess = {
            if(it.curPage != 1){
                _articlePage.value?.run {
                    it.datas = datas.apply {
                        addAll(it.datas)
                    }
                }
            }
            _articlePage.value = it
        },onError = {
            ToastUtils.showToast(it)
        })
    }

}


