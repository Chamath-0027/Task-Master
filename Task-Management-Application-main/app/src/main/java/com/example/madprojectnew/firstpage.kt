package com.example.madprojectnew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class firstpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firstpage)

       // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
       //     val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
       //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
         //   insets
       // }
  val doButton: Button = findViewById(R.id.doButton)
        doButton.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}