package com.example.madprojectnew

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class timerPage : AppCompatActivity() {

    private var countDownTimer: CountDownTimer? = null
    private var isTimerRunning: Boolean = false
    private var timeLeftInMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_page)

        // Handling the window insets (padding for system bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find views
        val timerEditText: EditText = findViewById(R.id.timerEditText)
        val timerDisplay: TextView = findViewById(R.id.timerDisplay)
        val startButton: Button = findViewById(R.id.startTimerButton)
        val stopButton: Button = findViewById(R.id.stopTimerButton)

        // Start Timer Button Click Listener
        startButton.setOnClickListener {
            if (!isTimerRunning) {
                val timeInMinutes = timerEditText.text.toString().toLongOrNull()
                if (timeInMinutes != null) {
                    timeLeftInMillis = timeInMinutes * 60000 // Convert minutes to milliseconds
                    startTimer(timeLeftInMillis, timerDisplay)
                } else {
                    timerEditText.error = "Please enter a valid number"
                }
            }
        }

        // Stop Timer Button Click Listener
        stopButton.setOnClickListener {
            if (isTimerRunning) {
                stopTimer()
            }
        }
    }

    // Start Timer Function
    private fun startTimer(timeInMillis: Long, timerDisplay: TextView) {
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerDisplay(timerDisplay)
            }

            override fun onFinish() {
                isTimerRunning = false
                timerDisplay.text = "00:00"
            }
        }.start()

        isTimerRunning = true
    }

    // Stop Timer Function
    private fun stopTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
    }

    // Update Timer Display
    private fun updateTimerDisplay(timerDisplay: TextView) {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        timerDisplay.text = timeFormatted
    }
}

open class AppCompatActivity {

}
