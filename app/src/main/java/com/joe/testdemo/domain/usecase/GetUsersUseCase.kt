package com.joe.testdemo.domain.usecase

import androidx.paging.PagingData
import com.joe.testdemo.domain.model.User
import com.joe.testdemo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    operator fun invoke(pageCount: Int = NETWORK_PAGE_SIZE): Flow<PagingData<User>> =
        userRepository.getUsers(pageCount = pageCount)

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}