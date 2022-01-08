package com.example.calllogger.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.calllogger.R
import com.example.calllogger.data.CallEntity
import kotlinx.android.synthetic.main.item_call.view.*

class CallAdapter : RecyclerView.Adapter<CallAdapter.ViewHolder> {


    private val items = mutableListOf<CallEntity>()

    val context: Context

    constructor(context: Context, callList: List<CallEntity>) : super() {
        this.context = context
        items.addAll(callList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_call, parent, false)
        Log.e("lista méret", items.size.toString())
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textviewDate.text = items[position].date
        holder.textviewNumber.text = items[position].number

        holder.buttonCall.setOnClickListener {
            val telNumber = items[position].number
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telNumber"))
            context.startActivity(intent)
        }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textviewDate: TextView = itemView.textview_datetime
        val textviewNumber: TextView = itemView.textview_number
        val buttonCall: Button = itemView.button_call
    }


}