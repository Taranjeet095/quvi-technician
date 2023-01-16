package com.quviservicestechnician.ui.fragment.serviceBookingDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quviservicestechnician.R
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.repository.HomeRepository
import com.quviservicestechnician.databinding.FragmentServiceDetailsBinding
import com.quviservicestechnician.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ServiceDetailsFragment : BaseFragment<ServiceDetailsViewModel,FragmentServiceDetailsBinding,HomeRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if (bundle == null){
            Log.e("Confirmation", "ConfirmationFragment did not receive traveler information")
            return
        }

        val args = ServiceDetailsFragmentArgs.fromBundle(bundle)
//        Log.d("arguments",args.data!!.id.toString())
    }

    override fun getViewModel(): Class<ServiceDetailsViewModel> = ServiceDetailsViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentServiceDetailsBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): HomeRepository {
        val token = runBlocking { userPreferences.accessToken.first() }
        val api = remoteDataSource.buildApi(AuthApi::class.java,token)
        return HomeRepository(api)
    }


}