package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding

enum class Operator {
    ADD,
    SUB,
    MUL,
    DIV,
    NONE
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var numberButtons: List<Button>
    private lateinit var operatorButtons: List<Button>
    private var operationsString = StringBuilder()
    private var operator: Operator = Operator.NONE
    private var isOperatorClicked = false
    private var firstBlock: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAc.setOnClickListener { clear() }
        initializeNumberButtons()
        initializeOperatorButtons()
        binding.buttonEquals.setOnClickListener { buttonEqualsClick() }
        binding.buttonBackspace.setOnClickListener { backSpaceButtonClick() }
    }

    private fun buttonEqualsClick() {
        val secondBlock =  operationsString.toString().toDouble()

        val result: Double = when(operator) {
            Operator.ADD -> firstBlock + secondBlock
            Operator.SUB -> firstBlock - secondBlock
            Operator.DIV -> firstBlock / secondBlock
            Operator.MUL -> firstBlock * secondBlock
            else -> 0.0
        }

        operationsString.clear()
        operationsString.append(result.toString())
        binding.resultTextview.text = operationsString
    }

    private fun initializeNumberButtons () {
        numberButtons = listOf(
            binding.buttonZero,
            binding.buttonOne,
            binding.buttonTwo,
            binding.buttonThree,
            binding.buttonFour,
            binding.buttonFive,
            binding.buttonSix,
            binding.buttonSeven,
            binding.buttonEight,
            binding.buttonNine,
            binding.buttonDot
        )

        for(i in numberButtons) {
            i.setOnClickListener { numberButtonClick(i) }
        }
    }

    private fun initializeOperatorButtons() {
        operatorButtons = listOf(
            binding.buttonPlus,
            binding.buttonMinus,
            binding.buttonX,
            binding.buttonDiv,
        )

        for(i in operatorButtons) {
            i.setOnClickListener { operatorButtonClick(i) }
        }
    }

    private fun operatorButtonClick(btn: Button) {
        operator = when (btn.text) {
            "X" -> Operator.MUL
            "/" -> Operator.DIV
            "+" -> Operator.ADD
            "-" -> Operator.SUB
            else -> Operator.NONE
        }

        isOperatorClicked = true
    }

    private fun numberButtonClick(btn: Button) {
        if(isOperatorClicked) {
            firstBlock = operationsString.toString().toDouble()

            operationsString.clear()
            isOperatorClicked = false
        }

        operationsString.append(btn.text)
        binding.resultTextview.text = operationsString
    }

    private fun clear() {
        operationsString.clear()
        binding.resultTextview.text = "0"
    }

    private fun backSpaceButtonClick() {
        if(operationsString.isNotEmpty()) {
            operationsString.deleteCharAt(operationsString.length -1)
            binding.resultTextview.text = operationsString
            if(operationsString.isEmpty()) {
                binding.resultTextview.text = "0"
            }
        }
    }
}