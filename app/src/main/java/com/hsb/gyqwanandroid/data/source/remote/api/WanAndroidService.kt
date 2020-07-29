package com.hsb.gyqwanandroid.data.source.remote.api

import com.hsb.gyqwanandroid.data.model.BaseResponse
import com.hsb.gyqwanandroid.data.model.LoginData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
 * @author gyq
 * @date 2020/7/28
 */
interface WanAndroidService {

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     * @param username user name
     * @param password password
     * @return 登陆数据
     */
    @POST("user/login")
    @FormUrlEncoded
    fun getLoginData(@Field("username") username: String?, @Field("password") password: String?): Call<BaseResponse<LoginData>>
}