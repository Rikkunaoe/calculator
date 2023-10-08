package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

@UnstableApi class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn0.setOnClickListener { setTextFields("0") }
        binding.btn1.setOnClickListener { setTextFields("1") }
        binding.btn2.setOnClickListener { setTextFields("2") }
        binding.btn3.setOnClickListener { setTextFields("3") }
        binding.btn4.setOnClickListener { setTextFields("4") }
        binding.btn5.setOnClickListener { setTextFields("5") }
        binding.btn6.setOnClickListener { setTextFields("6") }
        binding.btn7.setOnClickListener { setTextFields("7") }
        binding.btn8.setOnClickListener { setTextFields("8") }
        binding.btn9.setOnClickListener { setTextFields("9") }
        binding.minusBtn.setOnClickListener { setTextFields("-") }
        binding.plusBtn.setOnClickListener { setTextFields("+") }
        binding.multBtn.setOnClickListener { setTextFields("*") }
        binding.divideBtn.setOnClickListener { setTextFields("/") }
        binding.parenthesesOpenBtn.setOnClickListener { setTextFields("(") }
        binding.parenthesesCloseBtn.setOnClickListener { setTextFields(")") }
        binding.acBtn.setOnClickListener {
            binding.mathOperation.text = ""
            binding.resultText.text = ""}

        binding.backBtn.setOnClickListener {
            val str = binding.mathOperation.text.toString()
            if(str.isNotEmpty()) {
                binding.mathOperation.text = str.substring(0, str.length - 1)
            }
            binding.resultText.text = ""
        }

        binding.equalBtn.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble())
                    binding.resultText.text = longRes.toString()
                else
                    binding.resultText.text = result.toString()

            } catch (e:Exception) {
                Log.d("Ошибка", "Ошибка ${e.message}")

            }
        }

    }

    fun setTextFields(str: String) {
        if(binding.resultText.text != "") {
            binding.mathOperation.text = binding.resultText.text
            binding.resultText.text = ""
        }
        binding.mathOperation.append(str)
    }

}