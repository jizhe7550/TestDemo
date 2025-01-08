package com.joe.testdemo.data.gateway.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_entity")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val gender: String,
    val phone: String,
    @SerializedName("large_url")
    val largeUrl: String,
    @SerializedName("medium_url")
    val mediumUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,

)