package com.example.calculadora_imc

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var weightEditText: EditText
    lateinit var heightEditText: EditText
    lateinit var calculateButton: Button

    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val weight = weightEditText.text.toString().toFloat()
            val height = heightEditText.text.toString().toFloat()

            val result = weight / (height / 100).pow(2)

            resultTextView.text = String.format("%.2f", result)
        }
    }

}