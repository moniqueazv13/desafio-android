package com.picpay.desafio.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert
    fun insertAllUsers(usersList: List<UserModel>)

    @Query("SELECT * FROM usermodel")
    fun getAllUser(): Flow<List<UserModel>>

    @Query("DELETE FROM usermodel")
    fun deleteAll()
}
