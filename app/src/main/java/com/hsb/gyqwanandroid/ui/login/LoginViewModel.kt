package com.hsb.gyqwanandroid.ui.login

import android.util.Patterns
import androidx.lifecycle.*
import com.hsb.gyqwanandroid.data.LoginRepository
import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.util.extension.request

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        request(onRequest = {
            loginRepository.login(username, password)
        },onSuccess = {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = it.publicName))
        },onError = {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        })
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length>5
    }
}
