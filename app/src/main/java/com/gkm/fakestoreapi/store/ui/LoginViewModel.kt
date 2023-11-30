package com.gkm.fakestoreapi.store.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkm.fakestoreapi.logError.LogException
import com.gkm.fakestoreapi.store.data.LoginUseCase
import com.gkm.fakestoreapi.store.preference.AuthorizateCredentials
import com.gkm.fakestoreapi.store.preference.PreferenceDataStore
import com.gkm.fakestoreapi.store.preference.UserCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStore: PreferenceDataStore,
) : ViewModel() {

    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _switchRemember = MutableLiveData<Boolean>()
    val switchRemember:LiveData<Boolean> = _switchRemember

    private val _loginButton = MutableLiveData<Boolean>()
    val loginButton: LiveData<Boolean> = _loginButton

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    val readAuthorizade: StateFlow<AuthorizateCredentials> =
        dataStore.readAuthorizate.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AuthorizateCredentials("")
        )

    private fun saveCredentials(name: String, password: String, saveSwitch: Boolean) =
        viewModelScope.launch {
            dataStore.saveCredentials(name, password, saveSwitch)
        }

    private fun saveAuthorizade(token:String) = viewModelScope.launch {
        dataStore.saveAuthorizate(token)
    }

    val readCredential: StateFlow<UserCredentials> =
        dataStore.readCredentials.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserCredentials("", "", false)
        )

    fun loginChanged(user: String, password: String) {
        _user.value = user
        _password.value = password
        _loginButton.value = enableLogin(user, password)
    }

    fun switchChanged(remember:Boolean){
        _switchRemember.value = remember
    }

    private fun enableLogin(user: String, password: String): Boolean {
        return (user.length >= 4 && password.length >= 4)
    }

    private fun correctLogin(login: Boolean): Boolean {
        _isLoggedIn.value = login
        return login
    }


    fun onLoginSelected() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val result = loginUseCase(user.value!!, password.value!!)
                if(switchRemember.value!!) {
                    saveCredentials(user.value!!, password.value!!, switchRemember.value!!)
                    saveAuthorizade(result.token)
                }
                Log.i("correct", "result ok ${result.token}")
                Log.i("token", "autor token ${readCredential.value.name}")
                correctLogin(true)
            } catch (e: LogException) {
                Log.e("Error", "Error de login", e)

                correctLogin(false)
            } finally {
                _loading.value = false
            }
        }
    }
}