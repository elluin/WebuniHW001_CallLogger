package com.example.calllogger.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CallDao {

    @Query("SELECT * FROM calls")
    fun getAllCallItems(): List<CallEntity>

    @Insert
    fun insertCallItem(callEntity: CallEntity): Long



}