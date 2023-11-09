package com.gkm.fakestoreapi.store.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkm.fakestoreapi.store.data.LoginResponse
import com.gkm.fakestoreapi.store.data.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
):ViewModel() {

    private val _user = MutableLiveData<String>()
    val user :LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password :LiveData<String> = _password

    private val _loginButton = MutableLiveData<Boolean>()
    val loginButton:LiveData<Boolean> = _loginButton

    private val _Loading = MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _Loading

    fun loginChanged(user:String, password:String){
        _user.value = user
        _password.value = password
        _loginButton.value = enableLogin(user, password)
    }

    private fun enableLogin(user: String, password: String): Boolean {
        return user.length >= 4 && password.length >= 4
    }

    fun onLoginSelected(){
        viewModelScope.launch {
            _Loading.value = true

            try{
                val result = loginUseCase(user.value!!, password.value!!)
                if(result){
                    Log.i("correc","result ok")
                }
            }catch (e:Exception){
                Log.e("Error", "Error de login", e)
            }finally {
                _Loading.value = false
            }
        }
    }
}