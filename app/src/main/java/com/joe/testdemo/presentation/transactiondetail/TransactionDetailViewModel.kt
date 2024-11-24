package com.joe.testdemo.presentation.transactiondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.joe.testdemo.domain.model.TransactionModel
import com.joe.testdemo.domain.usecase.GetTransactionDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTransactionDetailUseCase: GetTransactionDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionDetailUIState())
    val uiState = _uiState.asStateFlow()

    val transactionId = savedStateHandle.toRoute<TransactionDetailRoute>().id

    init {
        viewModelScope.launch {
            getTransactionDetail(transactionId)
        }
    }

    private suspend fun getTransactionDetail(id: Int) {
        runCatching {
            getTransactionDetailUseCase(id)
        }.onSuccess {
            handleTransactionUI(it)
        }.onFailure {
            println(it.message)
        }
    }

    private fun handleTransactionUI(transaction: TransactionModel) {
        _uiState.update {
            it.copy(
                transaction = transaction
            )
        }
    }
}