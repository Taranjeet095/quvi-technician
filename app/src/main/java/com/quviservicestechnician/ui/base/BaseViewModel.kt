package com.quviservicestechnician.ui.base

import androidx.lifecycle.ViewModel
import com.quviservicestechnician.data.repository.BaseRepository

/**
 * Created by Akash Singhal Singh on 29-09-2022.
 * Mail Id : mormukutsinghji@gmail.com
 */
abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {
//    suspend fun logout(api: UserApi) = repository.logout(api)
}