package com.quviservicestechnician.ui.fragment.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.UserUpdateRepository
import com.quviservicestechnician.data.response.TechnicianUpdateDetailResponse
import com.quviservicestechnician.data.response.UserInfoResponse
import com.quviservicestechnician.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserProfileViewModel(
    private val repository: UserUpdateRepository
) : BaseViewModel(repository) {
    private val _userUpdateResponse: MutableLiveData<Resource<TechnicianUpdateDetailResponse>> = MutableLiveData()
    val userUpdateResponse: LiveData<Resource<TechnicianUpdateDetailResponse>>
        get() = _userUpdateResponse

    fun userDetailsUpdate(
        name: String,
        email: String,
        country: String,
        country_code: String,
        phone: String,
//        image: File
    ) = viewModelScope.launch {
        _userUpdateResponse.value = Resource.Loading
        _userUpdateResponse.value = repository.userDetailsUpdate(name, email, country, country_code, phone)
    }

}