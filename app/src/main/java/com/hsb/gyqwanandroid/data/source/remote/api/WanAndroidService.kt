package com.hsb.gyqwanandroid.data.source.remote.api

import com.hsb.gyqwanandroid.data.model.*
import retrofit2.Call
import retrofit2.http.*

/**
 *
 * @author gyq
 * @date 2020/7/28
 */
interface WanAndroidService {

    @POST("user/login")
    @FormUrlEncoded
    fun getLoginData(@Field("username") username: String?, @Field("password") password: String?): Call<BaseResponse<LoginData>>

    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page")page:Int):Call<BaseResponse<PageResponse<Article>>>

    @GET("banner/json")
    fun getBannerList():Call<BaseResponse<List<BannerData>>>
}