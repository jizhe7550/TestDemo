package com.joe.testdemo.domain.model

data class User(
    val id: String,
    val name: String,
    val gender: String,
    val phone: String,
    val largeUrl: String?,
    val mediumUrl: String?,
    val thumbnailUrl: String?,
)
