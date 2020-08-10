package com.hsb.gyqwanandroid.data

import com.hsb.gyqwanandroid.data.model.Article
import com.hsb.gyqwanandroid.data.model.BannerData
import com.hsb.gyqwanandroid.data.model.BaseResponse
import com.hsb.gyqwanandroid.data.model.PageResponse
import com.hsb.gyqwanandroid.data.source.remote.HomeDataSource

/**
 *
 * @author gyq
 * @date 2020/8/8
 */
class HomeRepository(private val homeDataSource: HomeDataSource) {
    suspend fun getArticleList(page:Int):Result<BaseResponse<PageResponse<Article>>>{
        return homeDataSource.getArticleList(page)
    }

    suspend fun getBannerList():Result<BaseResponse<List<BannerData>>>{
        return homeDataSource.getBannerList()
    }
}