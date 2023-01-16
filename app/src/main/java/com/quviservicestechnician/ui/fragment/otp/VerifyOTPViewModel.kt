package com.quviservicestechnician.ui.fragment.otp

import androidx.lifecycle.ViewModel
import com.quviservicestechnician.data.repository.AuthRepository
import com.quviservicestechnician.data.repository.BaseRepository
import com.quviservicestechnician.ui.base.BaseViewModel

class VerifyOTPViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

}