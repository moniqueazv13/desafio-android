package com.picpay.desafio.android.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

open class BaseDataSource {
    protected fun <T> call(block: suspend FlowCollector<T>.() -> T): Flow<T> = flow {
        emit(block())
    }
}
