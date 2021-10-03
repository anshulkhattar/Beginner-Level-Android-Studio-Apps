package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Notes(@ColumnInfo(name = "text") val text:String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}