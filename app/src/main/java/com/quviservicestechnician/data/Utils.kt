package com.quviservicestechnician.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.ui.Action
import com.quviservicestechnician.ui.base.BaseFragment
import com.quviservicestechnician.ui.fragment.login.LoginFragment


fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun checkAppVersion(context: Context, app_version: String): Boolean {
    var pInfo: PackageInfo? = null
    return try {
        pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
        val version = pInfo.versionName

        //            String serverAppVersion = v1;          //appVersionModel.getApp_version()
        //            String currentAppVersion = v2;         //version
        val appversion: Array<String>
        appversion = app_version.split("\\.".toRegex()).toTypedArray()
        val current_version: Array<String>
        current_version = version.split("\\.".toRegex()).toTypedArray()
        var versionMatch = false
        for (i in 0 until if (appversion.size <= current_version.size) appversion.size else current_version.size) {
            versionMatch = Integer.valueOf(current_version[i]) > Integer.valueOf(
                appversion[i])
            if (versionMatch) {
                break
            } else {
                versionMatch = (Integer.valueOf(current_version[i]) === Integer.valueOf(
                    appversion[i]))
                if (!versionMatch)
                    break
            }
        }
        versionMatch
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        false
    }
}


///**For Show Tool Tip
// * @gravity TOP BOTTOM LEFT RIGHT CENTER
// * */
//
//var tooltip: Tooltip? = null
//fun showToolTip(view: View, context: Context, msg: String, duration: Long, gravity: String) {
//    tooltip?.dismiss()
//
//    tooltip = Tooltip.Builder(context)
//        .anchor(view, 0, 0, false)
//        .text(msg)
////            .styleId(R.style.ToolTipAltStyle)
//        .typeface(Typefaces[context, "fonts/poppins_semibold.ttf"])
////        .maxWidth(resources.displayMetrics.widthPixels / 2)
//        .arrow(true)
//        .floatingAnimation(Tooltip.Animation.DEFAULT)
//        .closePolicy(getClosePolicy())
//        .showDuration(duration)
//        .overlay(true)
//        .create()
//
//    tooltip
//        ?.doOnHidden {
//            tooltip = null
//        }
//        ?.doOnFailure { }
//        ?.doOnShown {}
//        ?.show(view, Tooltip.Gravity.valueOf(gravity), true)
//}
//
//private fun getClosePolicy(): ClosePolicy {
//    val builder = ClosePolicy.Builder()
//    builder.inside(true)
//    builder.outside(true)
//    builder.consume(true)
//    return builder.build()
//}

fun TwoButtonDialog(
    context: Context,
    title: String,
    msg: String,
    buttonName: String,
    listener: (position: Int, action: com.quviservicestechnician.ui.Action) -> Unit,
) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(msg)
    builder.setPositiveButton(buttonName) { dialog, which ->
        dialog.dismiss()
        listener(1, Action.DONE)
    }
    builder.setNeutralButton("Cancel") { dialog, _ ->
        dialog.dismiss()
        listener(0, Action.CANCEL)
    }
    val dialog: AlertDialog = builder.create()
    dialog.show()
    dialog.setCancelable(false)
}

fun base64ToBitmap(encodedImage: String): Bitmap {

    val decodedString: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    return decodedByte
}

fun OneButtonDialog(
    context: Context,
    title: String,
    msg: String,
    buttonName: String,
    listener: (position: Int, action: Action) -> Unit,
) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(msg)
    builder.setPositiveButton(buttonName) { dialog, which ->
        dialog.dismiss()
        listener(1, Action.DONE)
    }
    val dialog: AlertDialog = builder.create()
    dialog.show()
    dialog.setCancelable(false)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null,
) {
    when {
        failure.isNetworkError -> requireView().snackbar(
            "Please Check Your internet connection",
            retry
        )
        failure.errorCode == 401 -> {
            if (this is LoginFragment) {
                requireView().snackbar("You've enter incorrect email or password")
            } else {
                (this as BaseFragment<*, *, *>).logout()
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}