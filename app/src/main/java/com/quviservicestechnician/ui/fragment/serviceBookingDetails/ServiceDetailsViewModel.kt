package com.quviservicestechnician.ui.fragment.serviceBookingDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.HomeRepository
import com.quviservicestechnician.data.response.AcceptOrderResponse
import com.quviservicestechnician.data.response.NewBookingResponse
import com.quviservicestechnician.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ServiceDetailsViewModel(
    private val repository: HomeRepository
) : BaseViewModel(repository){
    private val _acceptBookingRes : MutableLiveData<Resource<AcceptOrderResponse>> = MutableLiveData()

    val acceptBookingRes: LiveData<Resource<AcceptOrderResponse>>
        get() = _acceptBookingRes

    fun acceptBooking(
        booking_id: Int
    ) = viewModelScope.launch {
        _acceptBookingRes.value = Resource.Loading
        _acceptBookingRes.value = repository.acceptBooking(booking_id)
    }



}