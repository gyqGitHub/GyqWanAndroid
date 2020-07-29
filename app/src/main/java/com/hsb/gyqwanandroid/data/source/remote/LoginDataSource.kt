package com.hsb.gyqwanandroid.data.source.remote

import com.hsb.gyqwanandroid.data.Result
import com.hsb.gyqwanandroid.data.model.BaseResponse
import com.hsb.gyqwanandroid.data.model.LoginData
import com.hsb.gyqwanandroid.data.source.remote.api.WanAndroidService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 * 远程请求数据以及请求结果处理
 */
class LoginDataSource {

    private val wanAndroidService = ServiceCreator.create(WanAndroidService::class.java)

    suspend fun login(username: String, password: String): Result<BaseResponse<LoginData>> {
        val response = wanAndroidService.getLoginData(username, password).await()
        return if(response.isSuccess()) Result.Success(response) else Result.Error(RuntimeException(response.errorMsg))
    }

    fun logout() {
    }
}

