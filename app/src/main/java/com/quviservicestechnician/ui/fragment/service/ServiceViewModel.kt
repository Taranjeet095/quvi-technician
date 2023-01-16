package com.quviservicestechnician.ui.fragment.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.ServiceRepository
import com.quviservicestechnician.data.response.ServiceListResponse
import com.quviservicestechnician.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ServiceViewModel(
    private val repository: ServiceRepository
) : BaseViewModel(repository) {

    private val _serviceResponse : MutableLiveData<Resource<ServiceListResponse>> = MutableLiveData()
    val serviceResponse : LiveData<Resource<ServiceListResponse>>
    get() = _serviceResponse

    fun getService(
    ) = viewModelScope.launch {
        _serviceResponse.value = Resource.Loading
        _serviceResponse.value = repository.getService()
    }
}