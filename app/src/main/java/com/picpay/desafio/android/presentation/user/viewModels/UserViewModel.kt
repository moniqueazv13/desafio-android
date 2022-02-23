package com.picpay.desafio.android.presentation.user.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.useCases.UserDataUseCase
import com.picpay.desafio.android.onSuccess
import com.picpay.desafio.android.presentation.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataUseCase: UserDataUseCase
) : ViewModel() {

    private val _onGetUsersSuccess = MutableLiveData<List<UserModel>>()
    val onGetUsersSuccess: LiveData<List<UserModel>> = _onGetUsersSuccess

    private val _onGetUsersError = SingleLiveEvent<Unit>()
    val onGetUsersError: LiveData<Unit> = _onGetUsersError

    fun getUserList() {
        viewModelScope.launch(Dispatchers.IO) {
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
