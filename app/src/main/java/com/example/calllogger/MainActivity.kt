package com.example.calllogger

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.calllogger.adapter.CallAdapter
import com.example.calllogger.data.AppDatabase
import com.example.calllogger.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CallAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestNeededPermission()


    }//ONCREATE

    //---------------------------------------------------------------------------------------------
    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    //----------------------------------------------------------------------------------------------
    override fun onDestroy() {
        unregisterReceiver(
            CallReceiver()
        )
        super.onDestroy()
    }

    //Recyclerview inicializálás ------------------------------------------------------------------
    private fun initRecyclerView() {
        Thread {
            val callList = AppDatabase.getInstance(this).callDao().getAllCallItems()

            runOnUiThread {
                adapter = CallAdapter(this, callList)
                binding.recyclerviewCallList.adapter = adapter
            }

        }.start()
    }

    //Futásidejű engedélykérések ------------------------------------------------------------------
    private fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.PROCESS_OUTGOING_CALLS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.PROCESS_OUTGOING_CALLS),
                101
            )
        } else {
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {

        when (requestCode) {
            101 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(this@MainActivity, "PERMISSION OK", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "NO PERMISSION", Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

}