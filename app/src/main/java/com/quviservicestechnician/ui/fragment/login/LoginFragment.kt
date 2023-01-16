package com.quviservicestechnician.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.quviservicestechnician.data.enable
import com.quviservicestechnician.data.handleApiError
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.AuthRepository
import com.quviservicestechnician.data.startNewActivity
import com.quviservicestechnician.databinding.FragmentLoginBinding
import com.quviservicestechnician.ui.activity.HomeActivity
import com.quviservicestechnician.ui.activity.ProfileActivity
import com.quviservicestechnician.ui.base.BaseFragment
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<LoginViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvCountryCode.text = ccp.selectedCountryCodeWithPlus
            ccp.setOnCountryChangeListener {
                tvCountryCode.text = ccp.selectedCountryCodeWithPlus
            }

            tvCountryCode.setOnClickListener {
                ccp.launchCountrySelectionDialog()
            }
//            tvLogin.enable(false)

//            tvLogin.setOnClickListener {
//                startActivity(Intent(requireActivity(),HomeActivity::class.java))
//            }
        }

        viewModel.loginRespons.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    if (it.value.code == 200){
                        lifecycleScope.launch {
                            viewModel.saveAuthToken(
                                it.value.results.token!!,
                            )
                            viewModel.saveUserProfile(it.value)
                            if (it.value.results.name.toString().equals("null")){
                                startActivity(Intent(requireActivity(), ProfileActivity::class.java))
                            }else {
                                requireActivity().startNewActivity(HomeActivity::class.java)
                            }
                        }
                    }
                    else{
                        Toast.makeText(requireContext(),
                            "Something went wrong",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure -> {
                    handleApiError(it) {
                        login()
                    }
                }
                else ->{}
            }
        })

        val phone =binding.etPhone.text.toString().trim()
//        binding.tvLogin.enable(phone.isNotEmpty())
        binding.tvLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val number= binding.etPhone.text.toString().trim()
        viewModel.login(number, binding.tvCountryCode.text.toString())
    }

    override fun getViewModel() = LoginViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() =
        AuthRepository(
            remoteDataSource.buildApi(AuthApi::class.java), userPreferences
        )
}