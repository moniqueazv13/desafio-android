package com.picpay.desafio.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.domain.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        fun createDataBase(context: Context) = Room
            .databaseBuilder(context, UserDB::class.java, "Jobs.db")
            .build()
            .userDao()
    }
}
