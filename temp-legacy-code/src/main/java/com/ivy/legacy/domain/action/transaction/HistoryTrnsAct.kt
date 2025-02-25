package com.ivy.wallet.domain.action.transaction

import com.ivy.base.legacy.Transaction
import com.ivy.data.db.dao.read.TransactionDao
import com.ivy.frp.action.FPAction
import com.ivy.frp.action.thenMap
import com.ivy.legacy.datamodel.temp.toDomain
import com.ivy.wallet.domain.pure.data.ClosedTimeRange
import javax.inject.Inject

class HistoryTrnsAct @Inject constructor(
    private val transactionDao: TransactionDao
) : FPAction<ClosedTimeRange, List<Transaction>>() {

    override suspend fun ClosedTimeRange.compose(): suspend () -> List<Transaction> = suspend {
        io {
            transactionDao.findAllBetween(
                startDate = from,
                endDate = to
            )
        }
    } thenMap { it.toDomain() }
}
