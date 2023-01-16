package com.quviservicestechnician.ui.fragment.service

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.quviservicestechnician.data.handleApiError
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.ServiceRepository
import com.quviservicestechnician.databinding.FragmentServiceBinding
import com.quviservicestechnician.ui.activity.DashboardActivity
import com.quviservicestechnician.ui.activity.HomeActivity
import com.quviservicestechnician.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ServiceFragment : BaseFragment<ServiceViewModel, FragmentServiceBinding,ServiceRepository> () {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewUI()
        binding.btnDone.setOnClickListener {
            startActivity(Intent(requireContext(),DashboardActivity::class.java))
        }
    }

    private fun viewUI() {
        viewModel.getService()
        viewModel.serviceResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    if (it.value.code==200){
                        binding.rcvServiceList.layoutManager = LinearLayoutManager(requireContext())
                        binding.rcvServiceList.adapter = ServiceAdapter(requireContext(), it.value.results)
                    }
                    else{
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure -> {
                    handleApiError(it){
                        viewModel.getService()
                    }
                }
                else -> {

                }
            }
        })
    }

    override fun getViewModel() = ServiceViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentServiceBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() : ServiceRepository {
        val token = runBlocking { userPreferences.accessToken.first() }
        val api = remoteDataSource.buildApi(AuthApi::class.java, token)
        return ServiceRepository(api)
    }


}