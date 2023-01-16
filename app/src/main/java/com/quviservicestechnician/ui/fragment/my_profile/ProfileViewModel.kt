package com.quviservicestechnician.ui.fragment.my_profile

import androidx.lifecycle.ViewModel
import com.quviservicestechnician.data.repository.AuthRepository
import com.quviservicestechnician.data.repository.HomeRepository
import com.quviservicestechnician.ui.base.BaseViewModel

class ProfileViewModel(
    private val repository: HomeRepository
) : BaseViewModel(repository) {
}