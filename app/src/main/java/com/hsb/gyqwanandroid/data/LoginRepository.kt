package com.hsb.gyqwanandroid.data

import com.hsb.gyqwanandroid.data.model.BaseResponse
import com.hsb.gyqwanandroid.data.model.LoginData
import com.hsb.gyqwanandroid.data.source.remote.LoginDataSource

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 * 仓库决定从本地还是远程数据源中获取数据
 */
class LoginRepository(val dataSource: LoginDataSource) {

    fun logout() {
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<BaseResponse<LoginData>> {
      return  dataSource.login(username, password)
    }

}
