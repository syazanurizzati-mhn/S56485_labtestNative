package com.example.question2labtest

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener(){
            val username = findViewById<EditText>(R.id.inUsername)
            val password = findViewById<EditText>(R.id.inPass)

            val emptyLevel = emptiness(username, password)
            if(emptyLevel>0){
                //which field is empty
                when(emptyLevel){
                    6 -> subToast("Username must no empty!")
                    7 -> subToast("Password must no empty!")
                    13 -> subToast("Username and Password must no empty!")
                }
            }else{
                    subToast("Success!")
                    val intent = Intent(this, SecondActivity::class.java).apply {
                    }
                    startActivity(intent)
                }
            }
        }


    private fun checkKey(username: String):Boolean {
        val db = openOrCreateDatabase("mydata", MODE_PRIVATE, null)
        val sql = "SELECT * FROM user where username='$username'"
        val cursor = db.rawQuery(sql, null)
        var out =false
        if(cursor.count>0)
            out=true
        return out
    }

    private fun emptiness(username: EditText, password: EditText):Int{
        var empty = 0

        if(username.text.isEmpty())
            empty +=6

        if(password.text.isEmpty())
            empty +=7

        return empty

    }

    private fun dbExists(c: Context, dbName: String):Boolean{
        val dbFile: File = c.getDatabasePath(dbName)
        return dbFile.exists()
    }

    private fun createDB(){
        val db = openOrCreateDatabase("mydata", MODE_PRIVATE, null)
        subToast("Database mydata created")
        val sqlText = "CREATE TABLE IF NOT EXISTS user" +
                "username VARCHAR(30) NOT NULL," +
                "password VARCHAR(30) NOT NULL " +
                ");"
        subToast("Table user created")
        db.execSQL(sqlText)
        var nextSQL = "INSERT INTO user(username, password) VALUES ('ahmad','ahmad1234');"
        db.execSQL(nextSQL)
        subToast("1 sample username added!")
    }


    private fun subToast(msg: String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }
}