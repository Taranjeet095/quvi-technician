package com.quviservicestechnician.ui.fragment.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.BookingRepository
import com.quviservicestechnician.data.response.BookingDetailsResponse
import com.quviservicestechnician.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class BookingViewModel(
    private val repository: BookingRepository
) : BaseViewModel(repository) {
    private val _bookingResponse : MutableLiveData<Resource<BookingDetailsResponse>> = MutableLiveData()

    val bookingResponse: LiveData<Resource<BookingDetailsResponse>>
        get() = _bookingResponse

    fun bookingRequest(
    ) = viewModelScope.launch {
        _bookingResponse.value = Resource.Loading
        _bookingResponse.value = repository.getBookings()
    }

}