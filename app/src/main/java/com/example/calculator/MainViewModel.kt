package com.example.calculator

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _number: MutableLiveData<String> = MutableLiveData("0")
    var number: LiveData<String> = _number


    private var operator: Operator = Operator.NONE
    private var isOperatorClicked = false
    private var firstBlock: Double = 0.0

    fun numberButtonClick(btn: Button) {
        if(isOperatorClicked) {
            firstBlock = _number.value!!.toDouble()

            _number.value = "0"
            isOperatorClicked = false
        }
        if(_number.value == "0") _number.value = ""
        _number.value += btn.text
    }

    fun clear() {
        _number.value = "0"
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
        if(_number.value!!.isNotEmpty()) {
            _number.value = _number.value!!.dropLast(1)

            if(_number.value!!.isEmpty()) _number.value = "0"
        }
    }

    fun equalsButtonClick() {
        val secondBlock = _number.value!!.toDouble()

        val result = when(operator) {
            Operator.ADD -> firstBlock + secondBlock
            Operator.SUB -> firstBlock - secondBlock
            Operator.DIV -> firstBlock / secondBlock
            Operator.MUL -> firstBlock * secondBlock
            else -> 0.0
        }

        _number.value = result.toString()
    }
}

enum class Operator {
    ADD,
    SUB,
    MUL,
    DIV,
    NONE
}