package com.example.question2labtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }

    }
}