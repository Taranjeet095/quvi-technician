package com.quviservicestechnician.data.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.quviservicestechnician.R
import com.quviservicestechnician.databinding.DialogCameraGalleryBinding
import com.quviservicestechnician.databinding.DialogYesNoBinding


interface CameraGalleryListener {
    fun onCameraClicked()
    fun onGalleryClicked()
}

interface YesNoListener{
    fun onAffirmative()
}

class YesNoDialog(context: Context, private val title: String,private var listener: YesNoListener) : Dialog(context, R.style.Theme_Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = DialogYesNoBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        val window = window
        val wlp = window!!.attributes
        window.attributes = wlp
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.apply {
            tvTitle.text = title
            tvYes.setOnClickListener {
                listener.onAffirmative()
                dismiss()
            }

            tvNo.setOnClickListener {
                dismiss()
            }
        }
    }
}

class CameraGalleryDialog(context: Context, private var listener: CameraGalleryListener) : Dialog(context, R.style.Theme_Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = DialogCameraGalleryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        val window = window
        val wlp = window!!.attributes
        window.attributes = wlp
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.tvCamera.setOnClickListener {
            listener.onCameraClicked()
            dismiss()
        }
        binding.tvGallery.setOnClickListener {
            listener.onGalleryClicked()
            dismiss()
        }
    }
}

/*

interface SelectionListener<in T>{
    fun onSelected(item: T)
}

class ListDialog<T>(context: Context,private val list: ArrayList<T>, private val listToShow: List<String>, private val listener: SelectionListener<T>)
    : Dialog(context, R.style.Theme_Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = DialogSimpleListBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        val window = window
        val wlp = window!!.attributes
        window.attributes = wlp
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val adapter = ListAdapter(listToShow,
            object : ListAdapter.OnSelected {
                override fun onSelected(position: Int) {
                    listener.onSelected(list[position])
                    dismiss()
                }
            }
        )

        binding.apply {
            rvItems.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
            rvItems.adapter = adapter
        }
    }
}*/


