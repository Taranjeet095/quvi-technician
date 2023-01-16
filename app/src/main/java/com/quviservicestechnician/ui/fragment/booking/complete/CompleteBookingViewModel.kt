package com.quviservicestechnician.ui.fragment.booking.complete

import androidx.lifecycle.ViewModel
import com.quviservicestechnician.data.repository.BookingRepository
import com.quviservicestechnician.ui.base.BaseViewModel

class CompleteBookingViewModel(
    val repository: BookingRepository
) : BaseViewModel(repository) {

}