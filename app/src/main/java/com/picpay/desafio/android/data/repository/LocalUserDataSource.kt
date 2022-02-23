package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {
    fun fetchUserLocalData(): Flow<List<UserModel>>
    fun insertUser(usersList: List<UserModel>)
    fun deleteUsers()
}
