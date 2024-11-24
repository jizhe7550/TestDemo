package com.joe.testdemo.presentation.transactiondetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joe.testdemo.domain.model.TransactionModel
import com.joe.testdemo.presentation.theme.TestDemoTheme

@Composable
fun TransactionDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TransactionDetailScreen(
        transaction = uiState.transaction,
        modifier = modifier,
    )
}

@Composable
private fun TransactionDetailScreen(
    transaction: TransactionModel?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (transaction == null) {
            Text(text = "No transaction detail")
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "id:${transaction.id}   ")
                    Text(text = "Date:${transaction.transactionDate}")
                }
                Row {
                    Text(text = transaction.summary)
                }
                Row {
                    Text(text = "debit:${transaction.debit} ")
                    Text(text = "credit:${transaction.credit}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionListScreenPreview() {
    TestDemoTheme {
        TransactionDetailScreen(
            transaction = TransactionModel(
                id = 1,
                transactionDate = "transactionDate",
                summary = "summary",
                debit = 123.00,
                credit = 453.00,
            ),
        )
    }
}
