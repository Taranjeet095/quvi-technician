package com.quviservicestechnician.ui.fragment.booking.inprogress

import androidx.lifecycle.ViewModel
import com.quviservicestechnician.data.repository.BookingRepository
import com.quviservicestechnician.ui.base.BaseViewModel

class InProgressBookingViewModel(
    val repository: BookingRepository
) : BaseViewModel(repository) {

}