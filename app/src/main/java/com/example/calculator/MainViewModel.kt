package com.example.calculator

import android.widget.Button
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var operationsString = StringBuilder()
    private var operator: Operator = Operator.NONE
    private var isOperatorClicked = false
    private var firstBlock: Double = 0.0

    fun numberButtonClick(btn: Button) {
        if(isOperatorClicked) {
            firstBlock = operationsString.toString().toDouble()

            operationsString.clear()
            isOperatorClicked = false
        }

        operationsString.append(btn.text)
    }

    fun clear() {
        operationsString.clear()
    }

    fun operatorButtonClick(btn: Button) {
        operator = when(btn.text) {
            "X" -> Operator.MUL
            "/" -> Operator.DIV
            "+" -> Operator.ADD
            "-" -> Operator.SUB
            else -> Operator.NONE
        }

        isOperatorClicked = true
    }

    fun backSpaceButtonClick() {
        if(operationsString.isNotEmpty()) {
            operationsString.deleteCharAt(operationsString.length -1)
        }
    }

    fun equalsButtonClick() {
        val secondBlock = operationsString.toString().toDouble()

        val result = when(operator) {
            Operator.ADD -> firstBlock + secondBlock
            Operator.SUB -> firstBlock - secondBlock
            Operator.DIV -> firstBlock / secondBlock
            Operator.MUL -> firstBlock * secondBlock
            else -> 0.0
        }

        operationsString.clear()
        operationsString.append(result.toString())
    }
}

enum class Operator {
    ADD,
    SUB,
    MUL,
    DIV,
    NONE
}