package com.hsb.gyqwanandroid.data.model

/**
 *
 * @author gyq
 * @date 2020/7/28
 */
data class LoginData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val coinCount: Int,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)