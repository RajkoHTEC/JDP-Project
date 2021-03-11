package com.htec.jdp

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    private lateinit var requestPermissionLauncher : ActivityResultLauncher<String>
    private lateinit var feature : TextView

    companion object {
        const val PERMISSION_REQUEST_CODE = 2
        const val REQUESTED_PERMISSION = Manifest.permission.READ_SMS
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnTryFeature).setOnClickListener {
            doFeature()
        }
        feature = findViewById(R.id.tvFeature)

        //startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))

        // Register the permissions callback, which handles the user's response to the
        // system permissions dialog. Save the return value, an instance of
        // ActivityResultLauncher. You can use either a val, as shown in this snippet,
        // or a lateinit var in your onAttach() or onCreate() method.

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    Toast.makeText(
                        applicationContext,
                        "You have just grant permission, you may now use our feature",
                        Toast.LENGTH_SHORT
                    ).show()
                    doFeature()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "This feature is unavailable until you grant access",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }

        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent().apply {
                action = Intent.ACTION_AUTO_REVOKE_PERMISSIONS
                data = Uri.fromParts("package", packageName, null)
            })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(applicationContext, "Granted", Toast.LENGTH_SHORT).show()
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                    Toast.makeText(applicationContext, "Cancel", Toast.LENGTH_SHORT).show()
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    private fun doFeature() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(this@MainActivity, REQUESTED_PERMISSION) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    feature.setText("Feature works")
                }
                shouldShowRequestPermissionRationale(REQUESTED_PERMISSION) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                   showInContextUI()

                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(REQUESTED_PERMISSION)
                }
            }
        }
    }

    private fun showInContextUI() {
        AlertDialog.Builder(this@MainActivity)
            .setTitle("About permission")
            .setMessage("We need that permission in order to use this feature...")
            .setNegativeButton("Cancel") { param1, _ ->
                param1.dismiss()
            }
            .setPositiveButton("Grant permission") { _, _ ->
                startActivity(Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package", packageName, null)
                })
            }
            .create()
            .show()
    }
}
