package com.picpay.desafio.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.useCases.UserDataUseCase
import com.picpay.desafio.android.presentation.user.viewModels.UserViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelRuleTestShould : CoroutineRuleTest() {

    private lateinit var vm: UserViewModel
    private val service = mockk<UserDataUseCase>()
    private val userListList = mockk<List<UserModel>>()

    override fun setup() {
        super.setup()
        vm = UserViewModel(service)
    }

    @Test
    fun fetchUserListingMockSuccess() {
        vm.run {
            coEvery { service.getUsers() } returns flow { emit(userListList) }
            getUserList()
            coVerify { service.getUsers() }
            assertEquals(userListList, onGetUsersSuccess.getValueForTest())
        }
    }

    @Test
    fun fetchUserListingMockError() {
        vm.run {
            coEvery { service.getUsers() } returns flow { throw Exception() }
            getUserList()
            coVerify { service.getUsers() }
            assertNull(onGetUsersError.getValueForTest())
        }
    }
}
fun <T> LiveData<T>.getValueForTest(): T? {
    var value: T? = null
    var observer = Observer<T> {
        value = it
    }
    observeForever(observer)
    removeObserver(observer)
    return value
}