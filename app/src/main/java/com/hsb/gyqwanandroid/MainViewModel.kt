package com.hsb.gyqwanandroid

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException

/**
 *
 * @author gyq
 * @date 2020/7/18
 */
class MainViewModel : ViewModel() {

    fun userNeedsDocs() {
        try {
            viewModelScope.launch(CoroutineExceptionHandler { _, e -> log(e.message) }) {
                //            testCoroutineText()
                //            fetchDocs()
                //            fetTwoDoc()

//                try {
//                    testException()
//                } catch (e: Exception) {
//                    Log.e("gyq","viewModelScope协程作用域处理异常：${e.message}")
//                }

//                testAnotherException()

            }
        } catch (e: Exception) {
            //对于执行testException()时，supervisorScope 和 coroutineScope 抛出的异常都不能捕获，
            //因为coroutineScope时异常最多到协程作用域，而supervisorScope则要求要在发生异常的协程内处理
            Log.e("gyq", "viewModelScope 作用域之外处理异常：${e.message}")
        }

        testOtherException()


//        val async = viewModelScope.async { fetchDocs() }
//        async.await()//常规函数无法调用await()，所以一般常规函数调用launch()函数来开启协程
        //launch 和 async 之间的很大差异是它们对异常的处理方式不同。async 期望最终是通过调用 await 来获取结果 (或者异常)，
        // 所以默认情况下它不会抛出异常。这意味着如果使用 async 启动新的协程，它会静默地将异常丢弃。
    }

    fun testCoroutineText() {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        Log.e("gyq", scope.coroutineContext.toString())
        val job = scope.launch(Dispatchers.IO) {
            Log.e("gyq", coroutineContext.toString())
            launch {
                Log.e("gyq", coroutineContext.toString())
            }
        }
    }

    private suspend fun testException() {
        //不管使用 supervisorScope 还是 coroutineScope 都需要进行异常捕获
        try {
            coroutineScope {
                launch {
                    Log.e("gyq", "launch1开始执行")
                    delay(1000)
                    Log.e("gyq", "开始报异常了")
//                    try {
                    throw NullPointerException("我是空指针异常")
//                    } catch (e: Exception) {
//                    }
                    Log.e("gyq", "还能执行吗？")
                }

                launch {
                    Log.e("gyq", "launch2开始执行")
                    delay(2000)
                    Log.e("gyq", "异常不影响我的执行")
                }
            }

            coroutineScope {
                launch {
                    Log.e("gyq", "launch3开始执行")
                }
            }
            Log.e("gyq", "testException执行完了")
        } catch (e: Throwable) {
            //coroutineScope 能捕获成功 supervisorScope 不能捕获到因为都没有抛出来
            Log.e("gyq", "顶部协程捕获异常信息-->${e.message}")
        }
    }

    private suspend fun fetTwoDoc() {
        Log.e("gyq", "开始：")
        coroutineScope {
            launch {
                Log.e("gyq", "launch启动新协程")
                delay(2000)
                cancel("手动取消")
                fetchDocs()
            }
            //不启动新的协程，将阻塞
            Log.e("gyq", "我没有启动新协程就执行挂起函数，下面开始阻塞。。。。")
//            delay(1000)
            val async = async {
                Log.e("gyq", "async启动新协程")
                delay(5000)
                fetchDocs()
                return@async "gyq"
            }
            //使用了launch 和 async开启新协程，使得这前者launch启动的协程不会阻塞async启动的协程
            Log.e("gyq", "我也不会被其他协程所阻塞")
            //就算不调用await()，async也会执行
            Log.e("gyq", "async返回的结果 -- ${async.await()}")
            Log.e("gyq", "我会被async.await()阻塞吗？")
        }
        //coroutineScope 也是一个挂起函数，会先将自己挂起，等待内部启动的所有协程完成
        Log.e("gyq", "结束：这里会被阻塞吗？会的")
    }

    private suspend fun fetchDocs() {
        logThreadInfo(1)
        val result = get("www.baidu.com")
        logThreadInfo(2)
    }

    suspend fun get(url: String) {
        logThreadInfo(3)
        withContext(Dispatchers.IO) {
            logThreadInfo(4)
        }
        logThreadInfo(5)
    }

    private fun logThreadInfo(msg: Int = 0) {
        Log.e("gyq", "${Thread.currentThread().name}---$msg")
    }

    private suspend fun testAnotherException() {
        //根协程(CoroutineScope 实例或 supervisorScope的直接子协程)时，async启动的协程不会自动抛出异常，而是在调用await()方法时才抛出
        //CoroutineScope的构造参数可以Job()或Supervisor()
//        val async = CoroutineScope(Job()).async { throw NullPointerException("空指针异常") }
//        try {
//            async.await()
//        } catch (e: Exception) {
//            Log.e("gyq","await()没有被注释掉时，才能捕获该异常：${e.message}")
//        }

//        supervisorScope{
//            val async = async { throw NullPointerException("空指针异常") }
//            try {
//                async.await()//真正抛出异常的地方在这
//            } catch (e: Exception) {
//                Log.e("gyq","捕获 supervisorScope 中 async 启动的协程的异常：${e.message}")
//            }
//        }

        //coroutineScope 如果使用是，则异常会在层级之间传递
//        try {
//            coroutineScope{
//                //由于是 coroutineScope ，async 启动的协程中产生的异常将在层级中传播
//                val async = async { throw NullPointerException("空指针异常") }
//                try {
//                    Log.e("gyq","下面执行await()")
//                    async.await()//真正抛出异常的地方在这
//                } catch (e: Exception) {
//                    Log.e("gyq","捕获 coroutineScope 中 async 启动的协程的异常：${e.message}")
//                }
//            }
//        } catch (e: Exception) {
//            Log.e("gyq","协程作用域内捕获的异常：${e.message}")
//        }

        supervisorScope {
            launch {
                async {
                    log("launch1")
                    throw NullPointerException("空指针")
                }
                launch {
                    delay(100)
                    log("launch2")
                }
            }

            launch {
                delay(1000)
                log("launch3")
            }

            async {
                delay(1500)
                log("launch4")
            }
        }

    }

    private fun testOtherException() {
        //结构化并发只有用 supervisorScope 和 coroutineScope 两个挂起函数实现????
        val scope = CoroutineScope(SupervisorJob())
        scope.launch(CoroutineExceptionHandler { _, e -> log("第一个scope.launch ${e.message}") }) {
            async {
                log("launch1")
                throw NullPointerException("空指针")
            }
            launch {
                delay(100)
                log("launch2")
            }
        }

        scope.launch(CoroutineExceptionHandler { _, e -> log("第二个scope.launch ${e.message}") }) {
            delay(200)
            log("launch3")
            async {
                throw IllegalArgumentException("非根协程的async()直接报异常")
            }
        }

        scope.async(CoroutineExceptionHandler { _, e -> log("第三个scope.launch ${e.message}") }) {
            delay(1500)
            log("launch4")
            throw IndexOutOfBoundsException("async()启动的根协程将不会立即报出异常")
        }

        scope.launch(CoroutineExceptionHandler { _, e -> log("第四个scope.launch ${e.message}") }) {
            supervisorScope {
                launch {
                    try {
                        launch {
                            throw NullPointerException("直接抛异常")
                        }
                    } catch (e: Exception) {
                        log("try/catch 作用域构建器： ${e.message}")
                    }
                }

                //此时传入的异常处理不起作用
                val async= async(CoroutineExceptionHandler { _, e -> log(e.message) }) {
                    delay(1600)
                    log("launch5")
                    throw NullPointerException("根协程要在调用了await()才报异常")
                }
                //注释掉try/catch后，launch6，7，8将不会执行，因为异常没有被处理，继续往上报了
                try {
                    async.await()
                } catch (e: Exception) {
                    log("try/catch ${e.message}")
                }
                //去掉异常处理看看咯
                launch(CoroutineExceptionHandler { _, e -> log("supervisorScope.launch ${e.message}") }) {
                    log("launch6")
                    async(CoroutineExceptionHandler { _, e -> log(e.message) }){
                        log("launch7")
                        throw NullPointerException("非根协程的async()直接报异常")
                    }
                }

                launch {
                    delay(100)
                    log("launch8")
                }

            }
        }
    }
}

fun log(num: Any?) {
    Log.e("gyq", num.toString())
//    println(num)
}