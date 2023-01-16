package com.quviservicestechnician.ui.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.quviservicestechnician.R
import com.quviservicestechnician.data.response.NewBookingDetailsResponse
import com.quviservicestechnician.databinding.NewBookingServiceRowListBinding
import com.quviservicestechnician.ui.Action
import com.quviservicestechnician.ui.base.EnumActionInterface

class NewServiceAdapter(
    private val con: Context,
    private val res: List<NewBookingDetailsResponse>,
    private val lisnear: EnumActionInterface
) : RecyclerView.Adapter<NewServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewBookingServiceRowListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(res[position], con)
    }

    override fun getItemCount(): Int = res.size

    inner class ViewHolder(val itemBinding: NewBookingServiceRowListBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(
            item: NewBookingDetailsResponse,
            con: Context) {
            itemBinding.apply {
                tvBookingId.text = "Booking ID : " + item.id.toString()
                tvServiceName.text = item.service.name
                tvSelectedSlot.text = item.booked_date + item.booked_end_time
                tvServiceRate.text = item.service.price.toString()
                tvLocation.text = item.address.address1 + " " + item.address.address2 + ", " + item.address.postcode
//                tvLocation.text = item.address_id.toString()
                tvAccept.setOnClickListener {
                    lisnear.onClick(item.id,"", Action.ACCEPT)
                }

                root.setOnClickListener {
                    val bundle = bundleOf("data" to item)
//                    Navigation.findNavController(itemView.rootView).navigate(R.id.booking_details, bundle)
                    Navigation.findNavController(itemView.rootView).navigate(R.id.booking_details,bundle)
                }
            }

        }
    }

}