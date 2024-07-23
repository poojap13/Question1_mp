package com.example.question1_cal

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var number1EditText: EditText
    private lateinit var number2EditText: EditText
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.result)
        number1EditText = findViewById(R.id.number1)
        number2EditText = findViewById(R.id.number2)
        spinner = findViewById(R.id.spinner)

        val operations = arrayOf("Addition", "Subtraction", "Multiplication", "Division")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onCalculateClick(view: View) {
        val number1 = number1EditText.text.toString().toDoubleOrNull()
        val number2 = number2EditText.text.toString().toDoubleOrNull()
        val operation = spinner.selectedItem.toString()

        if (number1 != null && number2 != null) {
            val result = when (operation) {
                "Addition" -> number1 + number2
                "Subtraction" -> number1 - number2
                "Multiplication" -> number1 * number2
                "Division" -> if (number2 != 0.0) number1 / number2 else "Error: Division by zero"
                else -> "Error: Invalid operation"
            }
            resultTextView.text = result.toString()
        } else {
            resultTextView.text = "Error: Please enter valid numbers"
        }
    }
}
