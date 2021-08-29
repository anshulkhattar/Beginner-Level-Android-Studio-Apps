package com.rudra.triviaapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.rudra.triviaapp.models.Trivia

class DatabaseHandler(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "TriviaDatabase"
            private const val TABLE_NAME = "TriviaTable"
            private const val KEY_ID = "id"
            private const val KEY_NAME = "name"
            private const val KEY_ANSWER1 = "ans1"
            private const val KEY_ANSWER2 = "ans2"
            private const val KEY_TIME = "time"
        }

        override fun onCreate(db: SQLiteDatabase?) {
            val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                    + KEY_ANSWER1 + " TEXT," + KEY_ANSWER2 + " TEXT," + KEY_TIME + " TEXT"  + ")")
            db?.execSQL(CREATE_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }

        fun addTst(a1: String, a2: String, name: String, time: String): Long {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(KEY_NAME, name)
            contentValues.put(KEY_ANSWER1, a1)
            contentValues.put(KEY_ANSWER2, a2)
            contentValues.put(KEY_TIME, time)
            val success = db.insert(TABLE_NAME, null, contentValues)
            db.close()
            return success
        }

        //method to read data
        fun viewData():List<Trivia>{
            val list:ArrayList<Trivia> = ArrayList<Trivia>()
            val selectQuery = "SELECT  * FROM $TABLE_NAME"
            val db = this.readableDatabase
            var cursor: Cursor? = null
            try{
                cursor = db.rawQuery(selectQuery, null)
            } catch (e: SQLiteException) {
                db.execSQL(selectQuery)
                return ArrayList()
            }
            var userId: Int
            var name: String
            var a1: String
            var a2: String
            var time: String
            if (cursor.moveToFirst()) {
                do {
                    userId = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                    a1 = cursor.getString(cursor.getColumnIndex(KEY_ANSWER1))
                    a2 = cursor.getString(cursor.getColumnIndex(KEY_ANSWER2))
                    time = cursor.getString(cursor.getColumnIndex(KEY_TIME))
                    val model = Trivia(userId.toString(), name, a1, a2, time)
                    list.add(model)
                } while (cursor.moveToNext())
            }
            cursor.close()
            return list
        }
    }
