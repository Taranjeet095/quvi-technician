package com.quviservicestechnician.ui.fragment.booking.complete

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.quviservicestechnician.R
import com.quviservicestechnician.data.repository.PendingBookingResponse
import com.quviservicestechnician.data.response.CompleteBookingResponse
import com.quviservicestechnician.databinding.CompleteBookingRowListBinding
import com.quviservicestechnician.databinding.PendingBookingRowListBinding

class CompleteBookingAdapter(
    private val con: Context,
    private val complete: List<CompleteBookingResponse>
   ): RecyclerView.Adapter<CompleteBookingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(CompleteBookingRowListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(complete[position],con)
    }

    override fun getItemCount(): Int = complete.size
    class ViewHolder(val ItemView: CompleteBookingRowListBinding): RecyclerView.ViewHolder(ItemView.root) {
        fun bind(
            item: CompleteBookingResponse,
            con: Context) {
            ItemView.root.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.nav_complete_booking_details)
            }

        }

    }
}