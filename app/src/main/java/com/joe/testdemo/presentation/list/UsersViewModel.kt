package com.joe.testdemo.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.joe.testdemo.domain.model.User
import com.joe.testdemo.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _pagingUserState = MutableStateFlow(PagingData.empty<User>())
    val pagingUserState = _pagingUserState.asStateFlow()


    init {
        fetchData()

    }

    private fun fetchData() {
        viewModelScope.launch {
            getUsersUseCase()
                .catch {
                    println(it)
                }
                .cachedIn(viewModelScope)
                .collectLatest {
                    _pagingUserState.value = it
                }
        }
    }
}