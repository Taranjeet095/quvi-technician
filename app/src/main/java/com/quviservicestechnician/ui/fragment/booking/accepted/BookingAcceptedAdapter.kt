package com.quviservicestechnician.ui.fragment.booking.accepted

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quviservicestechnician.data.repository.PendingBookingResponse
import com.quviservicestechnician.databinding.PendingBookingRowListBinding

class BookingAcceptedAdapter(
   private val con: Context,
   private val pending: List<PendingBookingResponse>
   ):RecyclerView.Adapter<BookingAcceptedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PendingBookingRowListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pending[position],con)
    }

    override fun getItemCount(): Int  = pending.size

    class ViewHolder(val ItemView: PendingBookingRowListBinding):RecyclerView.ViewHolder(ItemView.root) {
        fun bind(
            item: PendingBookingResponse,
            con: Context) {
        }

    }

}