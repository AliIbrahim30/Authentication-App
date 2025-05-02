package com.example.authentication_app.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.authentication_app.data.remote.datastore.DataStoreManager
import com.example.authentication_app.repository.AuthRepository
import com.example.authentication_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _loginState = MutableLiveData<Resource<String>>()
    val loginState: LiveData<Resource<String>> = _loginState


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.postValue(Resource.Loading())
            val result = authRepository.login(email = email, password = password)
            if (result is Resource.Success) {
                dataStore.saveToken(result.data ?: "")
            }

            _loginState.postValue(result)
        }
    }

    val token: LiveData<String?> = dataStore.getToken.asLiveData()
}
