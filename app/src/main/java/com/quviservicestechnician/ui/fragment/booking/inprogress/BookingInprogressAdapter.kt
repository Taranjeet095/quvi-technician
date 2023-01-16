package com.quviservicestechnician.ui.fragment.booking.inprogress

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.quviservicestechnician.R
import com.quviservicestechnician.data.response.InprogresCompleteResponse
import com.quviservicestechnician.databinding.InprogressBookingRowListBinding

class BookingInprogressAdapter(
    private val con: Context,
    private val inprogress: List<InprogresCompleteResponse>
) : RecyclerView.Adapter<BookingInprogressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(InprogressBookingRowListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(inprogress[position],con)
    }

    override fun getItemCount(): Int  = inprogress.size

    class ViewHolder(val ItemView : InprogressBookingRowListBinding): RecyclerView.ViewHolder(ItemView.root) {
        fun bind(item: InprogresCompleteResponse,
                 con: Context) {
            ItemView.tvBookingId.text = "Request ID: " + item.id
            ItemView.tvServiceName.text = item.service.name
            ItemView.tvSelectedSlot.text = item.booked_date + "(" + item.booked_start_time + ")"
            ItemView.tvServiceRate.text = item.service.price.toString()
            ItemView.tvLocation.text = item.address_id.toString()
            ItemView.root.setOnClickListener {
                val bundle = bundleOf("data" to item)
                Navigation.findNavController(it).navigate(R.id.nav_inprogress_fragment,bundle)
            }
        }
    }
}