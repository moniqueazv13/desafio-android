package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserDAO
import com.picpay.desafio.android.domain.model.UserModel

class LocalUserDataSourceImp(private val userDao: UserDAO) : BaseDataSource(), LocalUserDataSource {

    override fun fetchUserLocalData() = userDao.getAllUser()
    override fun insertUser(usersList: List<UserModel>) = userDao.insertAllUsers(usersList)
    override fun deleteUsers() = userDao.deleteAll()
}
