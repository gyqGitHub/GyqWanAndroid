package com.hsb.gyqwanandroid.data.source.remote

import com.hsb.gyqwanandroid.data.Result
import com.hsb.gyqwanandroid.data.model.Article
import com.hsb.gyqwanandroid.data.model.Banner
import com.hsb.gyqwanandroid.data.model.BaseResponse
import com.hsb.gyqwanandroid.data.model.PageResponse
import com.hsb.gyqwanandroid.data.source.remote.api.WanAndroidService
import com.hsb.gyqwanandroid.util.extension.result
import retrofit2.await

/**
 *
 * @author gyq
 * @date 2020/8/6
 */
class HomeDataSource {
    private val wanAndroidService by lazy { ServiceCreator.create(WanAndroidService::class.java) }

    suspend fun getArticleList(page:Int):Result<BaseResponse<PageResponse<Article>>>{
        return wanAndroidService.getArticleList(page).await().result()
    }

    suspend fun getBannerList():Result<BaseResponse<List<Banner>>>{
        return wanAndroidService.getBannerList().await().result()
    }
}