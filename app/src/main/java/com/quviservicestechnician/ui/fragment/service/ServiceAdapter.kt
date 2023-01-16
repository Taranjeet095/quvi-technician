package com.quviservicestechnician.ui.fragment.service

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quviservicestechnician.data.response.ServiceListDataRes
import com.quviservicestechnician.databinding.ServiceRowListBinding

class ServiceAdapter(
    private val con: Context,
    private val data: List<ServiceListDataRes>
) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =ServiceRowListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding, con)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position],con)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(val ItemView:ServiceRowListBinding,
    val con: Context
    ): RecyclerView.ViewHolder(ItemView.root) {
        fun bind(data: ServiceListDataRes, con: Context) {
            ItemView.apply {
                tvTitle.text = data.name
                tvDescription.text = data.description
            }
        }

    }

}