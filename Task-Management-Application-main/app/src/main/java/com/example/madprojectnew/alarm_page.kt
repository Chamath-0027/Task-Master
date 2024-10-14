package com.example.madprojectnew

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class alarm_page : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_page)

        // Handling window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find views from the layout
        val timeEditText: EditText = findViewById(R.id.timeEditText)
        val setReminderButton: Button = findViewById(R.id.setReminderButton)

        // Set click listener for the button
        setReminderButton.setOnClickListener {
            val timeInMinutes = timeEditText.text.toString().toLongOrNull()
            if (timeInMinutes != null) {
                scheduleReminder(timeInMinutes)
            } else {
                Toast.makeText(this, "Please enter a valid time", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to schedule the reminder
    private fun scheduleReminder(timeInMinutes: Long) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Set the alarm to trigger after the specified time
        val triggerTime = System.currentTimeMillis() + (timeInMinutes * 60000)

        // Schedule the exact alarm
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )

        Toast.makeText(this, "Reminder set for $timeInMinutes minutes", Toast.LENGTH_SHORT).show()
    }
}
