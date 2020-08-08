package com.hsb.gyqwanandroid.data.model

/**
 *
 * @author gyq
 * @date 2020/8/8
 */
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)