package com.berbix.advancedsampleapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    private val permissions = arrayOf(Manifest.permission.CAMERA)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Request camera permissions
        if (!cameraPermissionGranted()) {
            ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_CODE_PERMISSIONS)
        }

        view.findViewById<View>(R.id.document_autocapture_off).setOnClickListener { navigate(DocumentAutoCaptureOffFragment()) }
        view.findViewById<View>(R.id.document_autocapture_on).setOnClickListener { navigate(DocumentAutoCaptureOnFragment()) }
        view.findViewById<View>(R.id.barcode_autocapture_off).setOnClickListener { navigate(BarcodeAutoCaptureOffFragment()) }
        view.findViewById<View>(R.id.barcode_autocapture_on).setOnClickListener { navigate(BarcodeAutoCaptureOnFragment()) }
        view.findViewById<View>(R.id.selfie_consent_not_granted).setOnClickListener { navigate(SelfieConsentNotGrantedFragment()) }
        view.findViewById<View>(R.id.selfie_consent_granted_autocapture_off).setOnClickListener { navigate(SelfieAutoCaptureOffFragment()) }
        view.findViewById<View>(R.id.selfie_consent_granted_autocapture_on).setOnClickListener { navigate(SelfieAutoCaptureOnFragment()) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!cameraPermissionGranted()) {
                Toast.makeText(requireContext(), "Camera permission not granted", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        }
    }

    private fun cameraPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(requireContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
    }

    private fun navigate(targetFragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, targetFragment, targetFragment::class.simpleName)
            .addToBackStack(this::class.simpleName)
            .commit()
    }
}