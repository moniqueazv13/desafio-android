package com.picpay.desafio.android.presentation.user.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.SingleLiveEvent
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.useCases.UserDataUseCase
import com.picpay.desafio.android.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(
    private val userDataUseCase: UserDataUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    private val _onGetUsersSuccess = MutableLiveData<List<UserModel>>()
    val onGetUsersSuccess: LiveData<List<UserModel>> = _onGetUsersSuccess

    private val _onGetUsersError = SingleLiveEvent<Unit>()
    val onGetUsersError: LiveData<Unit> = _onGetUsersError

    init {
        getUserList()
    }

    private fun getUserList() {
        launch {
            userDataUseCase.getUsers()
                .catch {
                    _onGetUsersError.postValue(null)
                }
                .onSuccess {
                    _onGetUsersSuccess.postValue(it)
                }
        }
    }
}
