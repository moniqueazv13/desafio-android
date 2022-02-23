package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    fun fetchUserData(): Flow<List<User>>
    fun fetchUserLocalData(): Flow<List<UserModel>>
    fun insertUser(usersList: List<UserModel>)
    fun deleteUser()
    fun fetchUser(): Flow<List<UserModel>>
}
