package com.example.calllogger.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "calls")
data class CallEntity(
    @PrimaryKey(autoGenerate = true) var itemId: Long?,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "number") var number: String
) : Serializable