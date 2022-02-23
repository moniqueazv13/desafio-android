package com.picpay.desafio.android

import com.picpay.desafio.android.data.repository.LocalUserDataSource
import com.picpay.desafio.android.data.repository.RemoteUserDataSource
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.model.UserModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest : CoroutineRuleTest() {
    private lateinit var repository: UserRepositoryImpl
    private val serviceLocalDataSource = mockk<LocalUserDataSource>()
    private val serviceRemoteDataSource = mockk<RemoteUserDataSource>()
    private val userLocalList: List<UserModel> = listOf(UserModel(0, "img_test", "Monique", "nena"))
    private val userRemoteList: List<User> = listOf(User(id = 0, img = "img_test", name = "Monique", username = "nena"))

    override fun setup() {
        super.setup()
        repository = UserRepositoryImpl(serviceRemoteDataSource, serviceLocalDataSource)
    }

    @Test
    fun fetchUserDataSuccess() = runBlockingTest {
        repository.run {
            coEvery { serviceRemoteDataSource.fetchUserData() } returns flow { emit(userRemoteList) }
            fetchUserData()
            coVerify { serviceRemoteDataSource.fetchUserData() }
        }
    }
}
