package com.hsb.gyqwanandroid.data.model

/**
 *
 * @author gyq
 * @date 2020/7/28
 */
open class BaseResponse<T> (val errorCode:Int,val errorMsg:String,val data:T){
    companion object{
        const val SUCCESS = 0
    }

    fun isSuccess():Boolean{
        return SUCCESS == errorCode
    }
}