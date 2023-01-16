package com.quviservicestechnician.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.quviservicestechnician.data.ToolTipPreferences
import com.quviservicestechnician.data.UserPreferences
import com.quviservicestechnician.data.network.RemoteDataSource
import com.quviservicestechnician.data.repository.BaseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected lateinit var userPreferences: UserPreferences
    protected lateinit var toolTipPreferences: ToolTipPreferences

    //    protected val remoteDataSource = LocalRemoteDataSource()
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        userPreferences = UserPreferences(requireContext())
        toolTipPreferences = ToolTipPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val fatory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, fatory).get(getViewModel())

        lifecycleScope.launch { userPreferences.accessToken.first() }
        return binding.root
    }

    fun logout() = lifecycleScope.launch {
//        val authToken = userPreferences.accessToken.first()
//        val api = remoteDataSource.buildApi(UserApi::class.java, authToken)
//        viewModel.logout(api)
        userPreferences.clear()
//        requireActivity().startNewActivity(NewLoginActivity::class.java)
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R

}