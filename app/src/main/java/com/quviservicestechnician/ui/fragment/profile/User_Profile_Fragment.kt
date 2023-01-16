package com.quviservicestechnician.ui.fragment.profile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.quviservicestechnician.R
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.network.Resource
import com.quviservicestechnician.data.repository.UserUpdateRepository
import com.quviservicestechnician.data.utils.CameraGalleryDialog
import com.quviservicestechnician.data.utils.CameraGalleryListener
import com.quviservicestechnician.data.utils.PERM_STORAGE
import com.quviservicestechnician.data.utils.REQUEST_GET_PHOTO
import com.quviservicestechnician.data.visible
import com.quviservicestechnician.databinding.FragmentUserProfileBinding
import com.quviservicestechnician.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class User_Profile_Fragment : BaseFragment<UserProfileViewModel,FragmentUserProfileBinding, UserUpdateRepository> () {

    private val GALLERY = 1
    private val CAMERA = 2
    lateinit var file : File
    var MEDIA_TYPE_IMAGE = "image/*".toMediaTypeOrNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clProfile.setOnClickListener {
            if (checkRequestPermissions()){
                openPopUp()
            }
        }

        viewModel.userUpdateResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.loader.visible(it is Resource.Loading)
            when(it){
                is Resource.Success -> {

                }

                is Resource.Failure -> {

                }
            }
        })

        viewUI()

        binding.btnNext.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.nav_service_fragment)
        }
    }

    private fun openPopUp() {
        CameraGalleryDialog(requireActivity(),
            object : CameraGalleryListener {
                override fun onCameraClicked() {
                    takePicture()
                }

                override fun onGalleryClicked() {
                    val intent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    intent.type = "image/*"
                    startActivityForResult(
                        Intent.createChooser(intent, "Pick From: "),
                        REQUEST_GET_PHOTO
                    )
                }
            }).show()
    }

    private fun takePicture() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA)
    }

    private fun createImageFile(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes)
        val wallpaperDirectory = File (
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        Log.d("fee", wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {
            wallpaperDirectory.mkdirs()
        }
        try
        {
            Log.d("heel", wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .getTimeInMillis()).toString() + ".png"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(requireContext(), arrayOf(f.getPath()), arrayOf("image/png"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

             file = File(f.absolutePath)

            return f.getAbsolutePath()
        }
        catch (e1: IOException){
            e1.printStackTrace()
        }
        return ""
    }

    private fun checkRequestPermissions(): Boolean {
        val camera = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        val storage = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val listPermissionsNeeded = ArrayList<String>()
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }

        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            requestPermissions(listPermissionsNeeded.toTypedArray(), PERM_STORAGE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERM_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openPopUp()
            } else {
                Toast.makeText(requireContext(), "Storage Permission Denied!", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA){
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            binding.ivProfile!!.setImageBitmap(thumbnail)
            createImageFile(thumbnail)
            Toast.makeText(requireContext(), "Photo Show!", Toast.LENGTH_SHORT).show()
        }
    }



    private fun viewUI() {
        val name = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()

        if (name.isNotEmpty()){
            binding.etFullName.error = "Enter Full Name"
        }
        else if(email.isNotEmpty()){
            binding.etEmail.error = "Enter Email"
        }
        else if (phone.isNotEmpty()){
            binding.etPhone.error = "Enter Phone Number"
        }
        else{
            viewModel.userDetailsUpdate(name, email, "India", "+91", phone)
        }
    }

    override fun getViewModel() = UserProfileViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserProfileBinding.inflate(inflater,container, false)

    override fun getFragmentRepository() : UserUpdateRepository{
        val token = runBlocking { userPreferences.accessToken.first() }
        val api = remoteDataSource.buildApi(AuthApi::class.java, token)
        return UserUpdateRepository(api)
    }

    companion object {
        private val IMAGE_DIRECTORY = "/nalhdaf"
    }

}