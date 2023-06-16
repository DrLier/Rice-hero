package com.example.capstone.camera

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.databinding.ActivityCameraBinding
import com.example.capstone.helper.CameraFactory
import com.example.capstone.helper.createFile
import com.example.capstone.helper.uriToFile
import com.example.capstone.mainpage.ui.diagnosis.DiagnosisPestDisease
import com.example.capstone.mainpage.ui.pestdisease.AdapterPlantDisease
import java.io.File

class CameraActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCameraBinding
    private var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var cameraViewModel: CameraViewModel
    private var imageCapture: ImageCapture? = null
    private var getFile: File? = null
    private lateinit var builder : AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val factory : CameraFactory = CameraFactory.getInstance()
        cameraViewModel = ViewModelProvider(this@CameraActivity, factory)[CameraViewModel::class.java]
        builder = AlertDialog.Builder(this@CameraActivity)

        binding.gallery.setOnClickListener { startGallery() }
        binding.capture.setOnClickListener { takePhoto() }

        cameraViewModel.checkPrediction().observe(this@CameraActivity, {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                if (it.prediction != 3) {
                    val intent = Intent(this@CameraActivity, DiagnosisPestDisease::class.java)
                    intent.putExtra(AdapterPlantDisease.DATA, it.prediction)
                    startActivity(intent)
                    cameraViewModel.changePredictionToNull()
                    finish()
                } else {
                    builder.setTitle("Information")
                        .setMessage("Tanaman Anda sehat, apakah Anda ingin kembali ke halaman awal?")
                        .setCancelable(true)
                        .setPositiveButton("Yes") { dialogInterface, _ ->
                            dialogInterface.dismiss()
                            cameraViewModel.changePredictionToNull()
                            finish()
                        }
                        .setNegativeButton("No") { dialogInterface, _ ->
                            dialogInterface.cancel()
                        }
                        .show()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Pilih Gambar")
        launcherGallery.launch(chooser)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == RESULT_OK) {
            val selectedImg : Uri = it.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@CameraActivity)
                getFile = myFile
            }
            if (getFile != null) {
                cameraViewModel.uploadImage(getFile as File)
                binding.progressBar.visibility = View.VISIBLE
                Toast.makeText(this@CameraActivity, "Please Wait...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider : ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.surface.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this@CameraActivity,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e : Exception) {
                Toast.makeText(
                    this@CameraActivity,
                    "Gagal memunculkan kamera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = createFile(application)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    cameraViewModel.uploadImage(photoFile)
                    binding.progressBar.visibility = View.VISIBLE
                    Toast.makeText(this@CameraActivity, "Please Wait...", Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        this@CameraActivity,
                        "Gagal mengambil gambar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}