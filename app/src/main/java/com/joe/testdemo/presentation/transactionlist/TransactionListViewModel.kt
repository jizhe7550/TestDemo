package com.joe.testdemo.presentation.transactionlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.testdemo.domain.model.TransactionModel
import com.joe.testdemo.domain.usecase.GetTransactionListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val getTransactionListUseCase: GetTransactionListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionListUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTransactionList()
        }
    }

    private suspend fun getTransactionList() =
        getTransactionListUseCase()
            .onEach { handleTransactionListUI(it) }
            .catch { println(it.cause) }
            .launchIn(viewModelScope)

    private fun handleTransactionListUI(transactionList: List<TransactionModel>) {
        _uiState.update {
            it.copy(
                transactionList = transactionList
            )
        }
    }
}