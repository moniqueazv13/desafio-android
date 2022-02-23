package com.picpay.desafio.android.presentation.user

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.hide
import com.picpay.desafio.android.presentation.user.adapters.UserListAdapter
import com.picpay.desafio.android.presentation.user.viewModels.UserViewModel
import com.picpay.desafio.android.show
import com.picpay.desafio.android.viewBinding
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    override val binding by viewBinding(ActivityMainBinding::inflate)
    private val userViewModel: UserViewModel by inject()

    override fun initView() = with(binding) {
        recyclerView.apply {
            adapter = UserListAdapter()
            layoutManager = LinearLayoutManager(context)
        }
        userListProgressBar.show()
        initObservers()
    }

    private fun initObservers() = with(userViewModel) {
        onGetUsersError.observe(
            this@MainActivity,
            Observer<Unit> {
                errorSignatureState()
            }
        )
        onGetUsersSuccess.observe(
            this@MainActivity,
            Observer<List<UserModel>> {
                successSignatureState(it)
            }
        )
    }

    private fun errorSignatureState() = with(binding) {
        userListProgressBar.hide()
        recyclerView.hide()
        Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT)
            .show()
    }

    private fun successSignatureState(data: List<UserModel>) = with(binding) {
        userListProgressBar.hide()
        (recyclerView.adapter as UserListAdapter).users = data
    }
}
