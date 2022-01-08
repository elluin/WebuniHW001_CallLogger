package com.example.calllogger

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestNeededPermission()

    }//ONCREATE

//Futásidejű engedélykérések ---------------------------------------------------------------------
private fun requestNeededPermission() {
    if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.PROCESS_OUTGOING_CALLS),
                101)
    } else {
    }
}

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            101 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this@MainActivity, "PERMISSION OK", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(this@MainActivity,
                            "NO PERMISSION", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}