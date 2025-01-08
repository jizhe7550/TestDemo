package com.joe.testdemo.data.mapper

import com.joe.testdemo.data.gateway.local.entity.UserEntity
import com.joe.testdemo.data.gateway.remote.dto.UserDto
import com.joe.testdemo.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {

    operator fun invoke(userDto: UserDto): UserEntity {
        return UserEntity(
            id = userDto.login.uuid,
            name = userDto.name.value,
            gender = userDto.gender ?: "",
            phone = userDto.phone ?: "",
            thumbnailUrl = userDto.picture.thumbnail ?: "",
            mediumUrl = userDto.picture.medium ?: "",
            largeUrl = userDto.picture.large ?: "",
        )
    }

    operator fun invoke(userEntity: UserEntity): User {
        return User(
            id = userEntity.id,
            name = userEntity.name,
            gender = userEntity.gender,
            phone = userEntity.phone,
            thumbnailUrl = userEntity.thumbnailUrl,
            mediumUrl = userEntity.mediumUrl,
            largeUrl = userEntity.largeUrl,
        )
    }

    operator fun invoke(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            name = user.name,
            gender = user.gender,
            phone = user.phone,
            thumbnailUrl = user.thumbnailUrl ?: "",
            mediumUrl = user.mediumUrl ?: "",
            largeUrl = user.largeUrl ?: "",
        )
    }
}


