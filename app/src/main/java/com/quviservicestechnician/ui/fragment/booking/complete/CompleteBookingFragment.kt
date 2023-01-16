package com.quviservicestechnician.ui.fragment.booking.complete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quviservicestechnician.R
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.repository.BookingRepository
import com.quviservicestechnician.databinding.FragmentCompleteBookingBinding
import com.quviservicestechnician.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class CompleteBookingFragment :
    BaseFragment<CompleteBookingViewModel, FragmentCompleteBookingBinding, BookingRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getViewModel()= CompleteBookingViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentCompleteBookingBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): BookingRepository {
        val token = runBlocking { userPreferences.accessToken.first() }
        val api = remoteDataSource.buildApi(AuthApi::class.java, token)
        return BookingRepository(api)
    }


}