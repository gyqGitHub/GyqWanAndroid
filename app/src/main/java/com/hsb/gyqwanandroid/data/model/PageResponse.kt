package com.hsb.gyqwanandroid.data.model

/**
 *
 * @author gyq
 * @date 2020/8/6
 */
class PageResponse<Item> constructor(
    val curPage: Int,
    val datas: List<Item>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)