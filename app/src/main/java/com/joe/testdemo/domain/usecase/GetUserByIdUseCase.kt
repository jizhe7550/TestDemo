package com.joe.testdemo.domain.usecase

import androidx.paging.PagingData
import com.joe.testdemo.domain.model.User
import com.joe.testdemo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

     suspend operator fun invoke(): Flow<PagingData<User>> = userRepository.getUsers(20)
}