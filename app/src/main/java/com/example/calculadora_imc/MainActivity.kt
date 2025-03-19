package com.example.calculadora_imc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    // Weight
    lateinit var removeWeightButton: Button
    lateinit var addWeightButton: Button
    lateinit var weightTextView: TextView

    // Height
    lateinit var heightSlider: Slider
    lateinit var heightTextView: TextView

    // Result
    lateinit var calculateButton: Button
    lateinit var resultTextView: TextView
    lateinit var descriptionTextView: TextView

    var weight = 74.0f
    var height = 170.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Busco los componentes en la vista y los guardo en sus respectivas variables
        // Weight
        removeWeightButton = findViewById(R.id.removeWeightButton)
        addWeightButton = findViewById(R.id.addWeightButton)
        weightTextView = findViewById(R.id.weightTextView)
        // Height
        heightSlider = findViewById(R.id.heightSlider)
        heightTextView = findViewById(R.id.heightTextView)
        // Result
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        // Asigno los listeners
        // Weight
        removeWeightButton.setOnClickListener {
            weight --
            weightTextView.text = "${weight.toInt()} kg"
        }
        addWeightButton.setOnClickListener {
            weight ++
            weightTextView.text = "${weight.toInt()} kg"
        }

        // Height
        heightSlider.addOnChangeListener { slider, value, fromUser ->
            height = value
            heightTextView.text = "${value.toInt()} cm"
        }

        // Result
        calculateButton.setOnClickListener {
            val result = weight / (height / 100).pow(2)

            resultTextView.text = String.format("%.2f", result)

            var colorId = 0
            var descriptionId = 0
            when (result) {
                in 0f..<18.5f -> {
                    colorId = R.color.bmi_underweight
                    descriptionId = R.string.bmi_underweight
                }
                in 18.5f..<25f -> {
                    colorId = R.color.bmi_normal_weight
                    descriptionId = R.string.bmi_normal_weight
                }
                in 25f..<30f -> {
                    colorId = R.color.bmi_overweight
                    descriptionId = R.string.bmi_overweight
                }
                in 30f..<35f -> {
                    colorId = R.color.bmi_obesity
                    descriptionId = R.string.bmi_obesity
                }
                else -> {
                    colorId = R.color.bmi_extreme_obesity
                    descriptionId = R.string.bmi_extreme_obesity
                }
            }
            descriptionTextView.text = getString(descriptionId)
            descriptionTextView.setTextColor(getColor(colorId))
            resultTextView.setTextColor(getColor(colorId))
        }
    }

}