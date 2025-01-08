package com.joe.testdemo.presentation.list

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.joe.testdemo.domain.model.User
import com.joe.testdemo.domain.usecase.GetUsersUseCase
import com.joe.testdemo.util.CoroutineMainDispatcherExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.reset
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UsersViewModelTest {

    private lateinit var underTest: UsersViewModel

    private val getUsersUseCase = mock<GetUsersUseCase>()

    @BeforeEach
    fun setUp() {
        initViewModel()
    }

    @AfterEach
    fun resetMocks(){
        reset(
            getUsersUseCase,
        )
    }

    private fun initViewModel() {
        underTest = UsersViewModel(getUsersUseCase)
    }

    @Test
    fun `test that fetchData updates pagingUserState when use case emits data`() = runTest {

        val fakeUsers = listOf(User("1", "User1", "Male", "123", null, null, null))
        val fakePagingData = PagingData.from(fakeUsers)
        val flow = flowOf(fakePagingData)
        whenever(getUsersUseCase()) doReturn flow

        initViewModel()

        underTest.pagingUserState.test {
            val emittedPagingData = awaitItem()
            assertThat(fakeUsers).isEqualTo(flowOf(emittedPagingData).asSnapshot())
        }

    }

//    @Test
//    fun `test that fetchData handles errors gracefully`() = runTest {
//        // Arrange
//        val exception = Exception("Test exception")
//        whenever(getUsersUseCase()) doThrow exception
//
//        // Act
//        underTest.fetchData()
//
//        // Assert
//        val result = underTest.pagingUserState.first()
//        assertEquals(PagingData.empty<UserModel>(), result) // 初始值为空
//    }

    companion object {
        @JvmField
        @RegisterExtension
        val extension = CoroutineMainDispatcherExtension(StandardTestDispatcher())
    }
}