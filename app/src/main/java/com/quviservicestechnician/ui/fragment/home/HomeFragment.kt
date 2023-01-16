package com.quviservicestechnician.ui.fragment.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.quviservicestechnician.data.handleApiError
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.HomeRepository
import com.quviservicestechnician.data.visible
import com.quviservicestechnician.databinding.FragmentHome2Binding
import com.quviservicestechnician.ui.Action
import com.quviservicestechnician.ui.base.BaseFragment
import com.quviservicestechnician.ui.base.EnumActionInterface
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHome2Binding,HomeRepository> (),
    EnumActionInterface{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mkl.visible(false)
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            } else {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            }
        }
        viewUi()

        viewModel.acceptBookingRes.observe(viewLifecycleOwner,Observer{
            binding.mkl.visible(it is Resource.Loading)
            when(it){
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.value.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        })
    }

    private fun viewUi() {
        viewModel.newBookingList()
        viewModel.newBookingResponse.observe(viewLifecycleOwner, Observer {
            binding.mkl.visible(it is Resource.Loading)
            when(it){
                is Resource.Success -> {
                    val res = it.value.results
                    if(res != null){
                        binding.rcvMyService.layoutManager = LinearLayoutManager(requireContext())
                        binding.rcvMyService.adapter = NewServiceAdapter(requireContext(),res,this)
                    }
                    else{
                        Toast.makeText(requireContext(), "No Service Available", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure -> {
                    handleApiError(it){
                        viewModel.newBookingList()
                    }
                }
                else -> {}
            }
        })
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )=  FragmentHome2Binding.inflate(inflater,container,false)

    override fun getFragmentRepository(): HomeRepository {
        val token = runBlocking { userPreferences.accessToken.first() }
        val api = remoteDataSource.buildApi(AuthApi::class.java,token)
        return HomeRepository(api)
    }

    override fun onClick(mbid1: Int, mbid: String, action: Action) {
        if (action == Action.ACCEPT){
            viewModel.acceptBooking(mbid1)
        }
    }


}