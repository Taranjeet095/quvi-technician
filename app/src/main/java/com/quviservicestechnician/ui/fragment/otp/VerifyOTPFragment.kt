package com.quviservicestechnician.ui.fragment.otp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quviservicestechnician.R
import com.quviservicestechnician.data.repository.AuthRepository
import com.quviservicestechnician.databinding.FragmentVerifyOTPBinding
import com.quviservicestechnician.ui.base.BaseFragment

class VerifyOTPFragment : BaseFragment<VerifyOTPViewModel,FragmentVerifyOTPBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun getViewModel() = VerifyOTPViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentVerifyOTPBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): AuthRepository {
        TODO("Not yet implemented")
    }

}