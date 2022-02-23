package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.data.repository.UserDataRepository

class UserDataUseCaseImpl(
    private val repository: UserDataRepository
) : UserDataUseCase {
    override suspend fun getUsers() = repository.fetchUser()
}
