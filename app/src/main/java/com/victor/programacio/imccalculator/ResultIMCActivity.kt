package com.victor.programacio.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.victor.programacio.R
import com.victor.programacio.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //Bajo peso
                tvResult.text = getString(R.string.title_low_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_low_weight)
            }

            in 18.51..24.99 -> { //Peso normal
                tvResult.text = getString(R.string.title_normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDescription.text = getString(R.string.description_normal_weight)
            }

            in 25.00..29.99 -> { //Sobrepeso
                tvResult.text = getString(R.string.title_high_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepedo))
                tvDescription.text = getString(R.string.description_high_weight)
            }

            in 30.00..99.99 -> { //Obesidad
                tvResult.text = getString(R.string.title_obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.description_obesity)
            }

            else -> { //Error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}