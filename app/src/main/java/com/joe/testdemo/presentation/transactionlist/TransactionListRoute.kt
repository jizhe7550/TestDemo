package com.joe.testdemo.presentation.transactionlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joe.testdemo.domain.model.TransactionModel
import com.joe.testdemo.presentation.theme.TestDemoTheme

@Composable
fun TransactionListScreen(
    onNavigateToTransactionDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TransactionListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TransactionListScreen(
        transactionList = uiState.transactionList,
        onNavigateToTransactionDetail = onNavigateToTransactionDetail,
        modifier = modifier,
    )
}

@Composable
private fun TransactionListScreen(
    transactionList: List<TransactionModel>,
    onNavigateToTransactionDetail: (Int) -> Unit,
    state: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = state,
    ) {

        items(
            count = transactionList.size,
            key = { index ->
                transactionList[index].id
            }
        ) { index ->

            val item = transactionList[index]

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onNavigateToTransactionDetail(item.id)
                    }
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "id:${item.id}   ")
                    Text(text = "Date:${item.transactionDate}")
                }
                Row {
                    Text(text = item.summary)
                }
                Row {
                    Text(text = "debit:${item.debit} ")
                    Text(text = "credit:${item.credit}")
                }
            }

            if (index < transactionList.size - 1) {
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionListScreenPreview() {
    TestDemoTheme {
        TransactionListScreen(
            transactionList = listOf(
                TransactionModel(
                    id = 1,
                    transactionDate = "transactionDate",
                    summary = "summary",
                    debit = 123.00,
                    credit = 453.00,
                )
            ),
            onNavigateToTransactionDetail = {},
        )
    }
}
