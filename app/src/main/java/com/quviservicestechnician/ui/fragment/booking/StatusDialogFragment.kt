package com.quviservicestechnician.ui.fragment.booking

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.quviservicestechnician.R
import com.quviservicestechnician.data.utils.ListenerInterface


class StatusDialogFragment(
     var listnear: ListenerInterface
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window!!.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.TOP)
        val p = dialog!!.window!!.attributes
        p.width = ViewGroup.LayoutParams.MATCH_PARENT
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        p.x = 500
        p.y = 200
        dialog!!.window!!.attributes = p
        val root = inflater.inflate(R.layout.fragment_status_dialog, container,false)
        root.findViewById<TextView>(R.id.tv_accepted).setOnClickListener {
            dismiss()
            val int = 1
            listnear.onClick(int)
        }
        root.findViewById<TextView>(R.id.tv_inprogress).setOnClickListener {
            dismiss()
            val int = 2
            listnear.onClick(int)
        }
        root.findViewById<TextView>(R.id.tv_complete).setOnClickListener {
            dismiss()
            val int = 3
            listnear.onClick(int)
        }
        root.findViewById<TextView>(R.id.tv_cancelled).setOnClickListener {
            dismiss()
            val int = 4
            listnear.onClick(int)
        }
        return root
    }
}