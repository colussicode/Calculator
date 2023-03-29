package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var numberButtons: List<Button>
    private lateinit var operatorButtons: List<Button>
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAc.setOnClickListener { clear() }
        initializeNumberButtons()
        initializeOperatorButtons()
        binding.buttonEquals.setOnClickListener { buttonEqualsClick() }
        binding.buttonBackspace.setOnClickListener { backSpaceButtonClick() }

        observeNumberLiveData()
    }

    private fun observeNumberLiveData() {
        viewModel.number.observe(this) { number ->
            binding.resultTextview.text = number
        }
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
        viewModel.operatorButtonClick(btn)
    }

    private fun numberButtonClick(btn: Button) {
        viewModel.numberButtonClick(btn)
    }

    private fun clear() {
        viewModel.clear()
    }

    private fun backSpaceButtonClick() {
        viewModel.backSpaceButtonClick()
    }

    private fun buttonEqualsClick() {
        viewModel.equalsButtonClick()
    }
}