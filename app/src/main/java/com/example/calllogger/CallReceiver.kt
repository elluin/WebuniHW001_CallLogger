package com.example.calllogger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.calllogger.data.AppDatabase
import com.example.calllogger.data.CallEntity
import java.util.*
import kotlin.concurrent.thread

class CallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val outNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
        Toast.makeText(context, outNumber, Toast.LENGTH_LONG).show()
        thread {
            AppDatabase.getInstance(context!!).callDao().insertCallItem(
                CallEntity(
                    null,
                    Date(System.currentTimeMillis()).toString(),
                    outNumber!!
                )
            )
        }

    }
}