package com.joe.testdemo.util

import com.joe.testdemo.domain.model.User

fun generateFakeUsers(count: Int): List<User> {
    val users = mutableListOf<User>()
    for (i in 1..count) {
        users.add(
            User(
                id = "id_$i",
                name = "User $i",
                gender = if (i % 2 == 0) "Male" else "Female",
                phone = "123-456-789$i",
                largeUrl = "https://example.com/images/large/$i.jpg",
                mediumUrl = "https://example.com/images/medium/$i.jpg",
                thumbnailUrl = "https://example.com/images/thumbnail/$i.jpg"
            )
        )
    }
    return users
}