package com.picpay.desafio.android

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.*

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: ((LayoutInflater) -> T)) =
    lazy(LazyThreadSafetyMode.NONE) { bindingInflater.invoke(layoutInflater) }

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

suspend fun <T> Flow<T>.onSuccess(action: suspend (value: T) -> Unit): Unit =
    collect {
        action(it)
    }
