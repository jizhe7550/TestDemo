package com.joe.testdemo.data.gateway.remote.dto

data class UserDto(
    val login: Login,
    val name: Name,
    val picture: Picture,
    val dob: Dob,
    val gender: String?,
    val phone: String?,
)

data class Name(
    val first: String?,
    val last: String?
) {
    val value: String
        get() = "$first $last"
}

data class Login(
    val uuid: String,
)

data class Picture(
    val large: String?,
    val medium: String?,
    val thumbnail: String?
)

data class Dob(
    val date: String?,
    val age: Int?
)
