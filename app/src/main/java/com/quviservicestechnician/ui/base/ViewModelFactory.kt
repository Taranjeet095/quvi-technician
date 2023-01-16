package com.quviservicestechnician.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.quviservicestechnician.data.repository.*
import com.quviservicestechnician.ui.fragment.booking.BookingViewModel
import com.quviservicestechnician.ui.fragment.booking.complete.CompleteBookingViewModel
import com.quviservicestechnician.ui.fragment.booking.inprogress.InProgressBookingViewModel
import com.quviservicestechnician.ui.fragment.home.HomeViewModel
import com.quviservicestechnician.ui.fragment.service.ServiceViewModel
import com.quviservicestechnician.ui.fragment.login.LoginViewModel
import com.quviservicestechnician.ui.fragment.my_profile.ProfileViewModel
import com.quviservicestechnician.ui.fragment.profile.UserProfileViewModel
import com.quviservicestechnician.ui.fragment.serviceBookingDetails.ServiceDetailsViewModel
import java.lang.IllegalArgumentException


class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                repository as AuthRepository
            ) as T
            modelClass.isAssignableFrom(UserProfileViewModel::class.java) -> UserProfileViewModel(
                repository as UserUpdateRepository
            ) as T
            modelClass.isAssignableFrom(ServiceViewModel::class.java) -> ServiceViewModel(
                repository as ServiceRepository
            ) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                repository as HomeRepository
            ) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(
                repository as HomeRepository
            ) as T
            modelClass.isAssignableFrom(ServiceDetailsViewModel::class.java) -> ServiceDetailsViewModel(
                repository as HomeRepository
            ) as T
            modelClass.isAssignableFrom(BookingViewModel::class.java) -> BookingViewModel(
                repository as BookingRepository
            ) as T
            modelClass.isAssignableFrom(InProgressBookingViewModel::class.java) -> InProgressBookingViewModel(
                repository as BookingRepository
            ) as T
            modelClass.isAssignableFrom(CompleteBookingViewModel::class.java) -> CompleteBookingViewModel(
                repository as BookingRepository
            ) as T

            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}