package com.hsb.gyqwanandroid.data.model

/**
 *
 * @author gyq
 * @date 2020/8/6
 */
class PageResponse<Item> constructor(
    var curPage: Int,
    var datas: ArrayList<Item>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)