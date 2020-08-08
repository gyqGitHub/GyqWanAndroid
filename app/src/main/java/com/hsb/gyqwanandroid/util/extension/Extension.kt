package com.hsb.gyqwanandroid.util.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsb.gyqwanandroid.data.Result
import com.hsb.gyqwanandroid.data.model.BaseResponse
import com.hsb.gyqwanandroid.util.loge
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.RuntimeException

/**
 * 单个接口请求
 */
fun <P,T: BaseResponse<P>>ViewModel.request(onRequest:suspend ()-> Result<T>, onSuccess:(P)->Unit, onError:(String)->Unit, onFinally:(()->Unit)? = null, onStart: (() -> Unit)? = null){
    viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
        try {
            onError(throwable.message!!)
        }catch (e:Throwable){
            loge(e.message)
        } finally {
            onFinally?.invoke()
        }
    }) {
        onStart?.invoke()
        val response = withContext(Dispatchers.IO){
            onRequest()
        }
        when(response){
            is Result.Success->onSuccess(response.data.data)
            is Result.Error->onError(response.exception.message!!)
        }
        onFinally?.invoke()
    }
}

fun <T>BaseResponse<T>.result():Result<BaseResponse<T>>{
   return if(isSuccess()) Result.Success(this) else Result.Error(RuntimeException(this.errorMsg))
}