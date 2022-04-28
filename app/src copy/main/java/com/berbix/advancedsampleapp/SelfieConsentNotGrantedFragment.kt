package com.berbix.advancedsampleapp

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.berbix.advanced.BerbixCameraView
import com.berbix.advanced.DocumentCameraFragment
import com.berbix.advanced.SelfieCameraFragment


class SelfieConsentNotGrantedFragment: SelfieCameraFragment() {
    override var autocaptureOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            startCamera()
        } catch (e: Exception) {
            Log.e("Berbix", "Starting camera failed")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_selfie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val flashlightButton = view.findViewById<Button>(R.id.flashlight_button)
        flashlightButton.setOnClickListener { toggleFlashlight() }

        val flipCameraDirectionButton = view.findViewById<Button>(R.id.flip_camera_button)
        flipCameraDirectionButton.setOnClickListener { flipCameraDirection() }

        val takePhotoButton = view.findViewById<Button>(R.id.take_photo_button)
        takePhotoButton.setOnClickListener {
            try {
                takePhoto()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error taking photo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override val berbixCameraView: BerbixCameraView
        get() = requireActivity().findViewById(R.id.berbix_camera_view)

    override fun onFaceNotFound(e: Exception) {
        /**
         * Add logic here to handle the FaceDetector encountering a failure
         * One possible solution could be to stop and restart the camera
         * by calling stopCamera() and then startCamera()
         */
    }

    override fun onPhotoCaptured(image: Image) {
        /**
         * Receive the image and do other processing e.g. upload to Berbix here
         */
    }
}