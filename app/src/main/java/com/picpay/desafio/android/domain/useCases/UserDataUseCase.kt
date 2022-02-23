package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserDataUseCase {
    suspend fun getUsers(): Flow<List<UserModel>>
}
