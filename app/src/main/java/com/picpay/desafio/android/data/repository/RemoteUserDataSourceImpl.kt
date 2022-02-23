package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.model.User

class RemoteUserDataSourceImpl(
    private val api: PicPayService
) : BaseDataSource(), RemoteUserDataSource {
    override fun fetchUserData() = call<List<User>> { api.getUsers() }
}
