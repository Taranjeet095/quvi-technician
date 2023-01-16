package com.quviservicestechnician.ui.fragment.booking.inprogress

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quviservicestechnician.R
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.repository.BookingRepository
import com.quviservicestechnician.databinding.FragmentInProgressBookingBinding
import com.quviservicestechnician.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class InProgressBookingFragment : BaseFragment<InProgressBookingViewModel,FragmentInProgressBookingBinding, BookingRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if(bundle == null){
            Log.e("Confirmation", "ConfirmationFragment did not receive traveler information")
            return
        }

        val args = InProgressBookingFragmentArgs.fromBundle(bundle)
//        Log.d("arguments", args.data!!.service.name.toString())
    }

    override fun getViewModel() = InProgressBookingViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentInProgressBookingBinding.inflate(inflater,container, false)

    override fun getFragmentRepository(): BookingRepository {
        val token = runBlocking { userPreferences.accessToken.first() }
        val api = remoteDataSource.buildApi(AuthApi::class.java, token)
        return BookingRepository(api)
    }


}