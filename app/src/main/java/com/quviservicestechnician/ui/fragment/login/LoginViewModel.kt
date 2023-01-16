package com.quviservicestechnician.ui.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.AuthRepository
import com.quviservicestechnician.data.response.UserInfoResponse
import com.quviservicestechnician.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<UserInfoResponse>> = MutableLiveData()
    val loginRespons: LiveData<Resource<UserInfoResponse>>
        get() = _loginResponse

    fun login(
        phone: String,
        country_code: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(phone,country_code)
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }

    suspend fun putString(key: String, value: String) {
        repository.putString(key, value)
    }

    suspend fun saveUserProfile(value: UserInfoResponse) {
        repository.saveUserProfile(value)
    }
}