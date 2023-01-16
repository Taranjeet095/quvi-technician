package com.quviservicestechnician.ui.fragment.my_profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.quviservicestechnician.R
import com.quviservicestechnician.data.UserPreferences
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.repository.AuthRepository
import com.quviservicestechnician.data.repository.HomeRepository
import com.quviservicestechnician.databinding.FragmentProfileBinding
import com.quviservicestechnician.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding, HomeRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivSetting.visibility = View.VISIBLE
        binding.ivHelp.visibility = View.VISIBLE
        setData()
    }

    private fun setData() {

        val userPreferences = UserPreferences(requireContext())
        val user = runBlocking { userPreferences.getUserProfile() }
        binding.apply {

            Glide.with(requireContext())
                .load(user.results.image)
                .transform(CenterCrop(), RoundedCorners(16))
//                .error(R.drawable.ic_account)
//                .placeholder(R.drawable.ic_account)
                .into(ivProfile)

            tvName.text =  user.results.name.toString() ?: ""
            tvCountry.text = user.results.country.toString() ?: ""
            tvMobile.text = user.results.phone
            tvEmail.text = user.results.email.toString()

            cl2Settings.setOnClickListener {
                if (clSettings.visibility == View.GONE){
                    clSettings.visibility = View.VISIBLE
                    ivSetting2.visibility = View.VISIBLE
                    ivSetting.visibility = View.GONE
                }
                else{
                    clSettings.visibility = View.GONE
                    ivSetting2.visibility = View.GONE
                    ivSetting.visibility = View.VISIBLE
                }
            }
            clHelp.setOnClickListener {
                if (clHelp2.visibility == View.GONE){
                    clHelp2.visibility = View.VISIBLE
                    ivHelp2.visibility = View.VISIBLE
                    ivHelp.visibility = View.GONE
                }
                else{
                    clHelp2.visibility = View.GONE
                    ivHelp2.visibility = View.GONE
                    ivHelp.visibility = View.VISIBLE
                }
            }
            tvEditAccount.setOnClickListener {

            }
        }
    }

    override fun getViewModel()= ProfileViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentProfileBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): HomeRepository {
        val token = runBlocking { userPreferences.accessToken.first() }
        val api = remoteDataSource.buildApi(AuthApi::class.java,token)
        return HomeRepository(api)
    }


}