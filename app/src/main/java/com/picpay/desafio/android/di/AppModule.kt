package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.UserDB
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.repository.*
import com.picpay.desafio.android.domain.useCases.UserDataUseCase
import com.picpay.desafio.android.domain.useCases.UserDataUseCaseImpl
import com.picpay.desafio.android.presentation.user.viewModels.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    viewModel { UserViewModel(get()) }
    factory<UserDataRepository> { UserRepositoryImpl(get(), get()) }
    factory<RemoteUserDataSource> { RemoteUserDataSourceImpl(get()) }
    factory<UserDataUseCase> { UserDataUseCaseImpl(get()) }
    factory<LocalUserDataSource> { LocalUserDataSourceImp(get()) }

    single { UserDB.createDataBase(androidContext()) }

    factory<PicPayService> {
        get<Retrofit>()
            .create(PicPayService::class.java)
    }
}

val applicationModules =
    listOf(
        appModule, securityModule
    )
