package com.quviservicestechnician.ui.fragment.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.quviservicestechnician.data.handleApiError
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.BookingRepository
import com.quviservicestechnician.data.response.BookingResultsResponse
import com.quviservicestechnician.data.utils.ListenerInterface
import com.quviservicestechnician.data.visible
import com.quviservicestechnician.databinding.FragmentBookingBinding
import com.quviservicestechnician.ui.base.BaseFragment
import com.quviservicestechnician.ui.fragment.booking.accepted.BookingAcceptedAdapter
import com.quviservicestechnician.ui.fragment.booking.complete.CompleteBookingAdapter
import com.quviservicestechnician.ui.fragment.booking.inprogress.BookingInprogressAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class BookingFragment : BaseFragment<BookingViewModel, FragmentBookingBinding, BookingRepository>(),
ListenerInterface{
    private lateinit var res : BookingResultsResponse

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        viewModel.bookingRequest()
        initView()
    }

    private fun initView() {
        val context = context as FragmentActivity
        val fm = context.supportFragmentManager
        binding.tvStatus.setOnClickListener {
            val dialogFragment = StatusDialogFragment(this)
            dialogFragment.show(fm,"action")
        }
    }

    override fun getViewModel() = BookingViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentBookingBinding.inflate(inflater,container, false)

    override fun getFragmentRepository(): BookingRepository {
        val token = runBlocking { userPreferences.accessToken.first() }
        val api = remoteDataSource.buildApi(AuthApi::class.java,token)
        return BookingRepository(api)
    }

    override fun onClick(int: Int) {
        if(int == 4){
            binding.tvStatus.text = "Cancelled >"
            callCancelled()
        }
        else if(int == 2){
            binding.tvStatus.text = "In Progress >"
            callInprogress()
        }
        else if (int == 3){
            binding.tvStatus.text = "Completed >"
            callComplete()
        }
        else{
            binding.tvStatus.text = "Accepted >"
            callAccepted()
        }

    }

    private fun callCancelled() {

    }

    private fun callInprogress() {
        viewModel.bookingResponse.observe(viewLifecycleOwner, Observer {
            binding.mkl.visible(it is Resource.Loading)
            when(it){
                is Resource.Success -> {
//                if (it.value.status.equals("200")){
                    res = it.value.results as BookingResultsResponse
                    binding.rcvBooking.layoutManager = LinearLayoutManager(requireContext())
                    binding.rcvBooking.adapter = BookingInprogressAdapter(requireContext(), res.inprogress)
//                }
//                else{
//                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
//                }
                }
                is Resource.Failure -> {
                    handleApiError(it){
                        viewModel.bookingRequest()
                    }
                }
                else -> {}
            }
        })
    }

    private fun callComplete() {
        viewModel.bookingResponse.observe(viewLifecycleOwner, Observer {
        binding.mkl.visible(it is Resource.Loading)
        when(it){
            is Resource.Success -> {
//                if (it.value.status.equals("200")){
                    res = it.value.results as BookingResultsResponse
                    binding.rcvBooking.layoutManager = LinearLayoutManager(requireContext())
                    binding.rcvBooking.adapter = CompleteBookingAdapter(requireContext(), res.complete)
//                }
//                else{
//                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
//                }
            }
            is Resource.Failure -> {
                handleApiError(it){
                    viewModel.bookingRequest()
                }
            }
            else -> {}
        }

    })
    }

    private fun callAccepted() {
        viewModel.bookingResponse.observe(viewLifecycleOwner, Observer {
            binding.mkl.visible(it is Resource.Loading)
            when(it){
                is Resource.Success -> {
//                    if (it.value.status.equals("200")){
                        res = it.value.results as BookingResultsResponse
                        binding.rcvBooking.layoutManager = LinearLayoutManager(requireContext())
                        binding.rcvBooking.adapter = BookingAcceptedAdapter(requireContext(), res.pending)
//                    }
//                    else{
//                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
//                    }
                }
                is Resource.Failure -> {
                    handleApiError(it){
                        viewModel.bookingRequest()
                    }
                }
                else -> {}
            }

        })
    }
}