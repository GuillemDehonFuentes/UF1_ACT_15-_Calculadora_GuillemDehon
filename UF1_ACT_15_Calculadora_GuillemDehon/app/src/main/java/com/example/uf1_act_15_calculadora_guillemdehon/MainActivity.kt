package com.example.uf1_act_15_calculadora_guillemdehon

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtResult: TextView
    private var input: String = ""
    private var operator: String = ""
    private var operand1: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResult = findViewById(R.id.txtResult)

        val buttonClickListener = { button: Button ->
            when (val buttonText = button.text.toString()) {
                "C" -> {
                    input = ""
                    operator = ""
                    operand1 = 0.0
                    txtResult.text = "0"
                }
                "=" -> calculateResult()
                "+", "-", "*", "/" -> {
                    if (input.isNotEmpty()) {
                        operand1 = input.toDouble()
                        operator = buttonText
                        input = ""
                    }
                }
                else -> {
                    input += buttonText
                    txtResult.text = input
                }
            }
        }

        val buttonIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnAdd, R.id.btnSub,
            R.id.btnMul, R.id.btnDiv, R.id.btnEqual, R.id.btnClear
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { buttonClickListener(it as Button) }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateResult() {
        if (input.isNotEmpty() && operator.isNotEmpty()) {
            val operand2 = input.toDouble()
            val result = when (operator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                "/" -> if (operand2 != 0.0) operand1 / operand2 else {
                    txtResult.text = "Error"
                    input = ""
                    return
                }
                else -> 0.0
            }
            txtResult.text = result.toString()
            input = result.toString()
            operator = ""
        }
    }
}
