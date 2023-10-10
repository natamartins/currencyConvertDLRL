package com.conversor.conversosdemoedas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conversor.conversosdemoedas.databinding.ActivityValueOfConvertionBinding

class ValueOfConvertion : AppCompatActivity() {

    private lateinit var binding: ActivityValueOfConvertionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValueOfConvertionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val same = i.extras?.getString("valueSame")
        val dola = i.extras?.getString("valueDola")
        val taxa = i.extras?.getString("valueTaxa")
        val tipe = i.extras?.getString("tipe")

        binding.resultSame.setText("R$" + same)
        binding.valueTaxa.setText("Taxa de " + taxa + "% no " + tipe)
        binding.valueDola.setText("Cotação do dólar: \$1,00 = " + dola)

        binding.btnClosed.setOnClickListener {
            val int = Intent(this, MainActivity::class.java)
            finish()
        }
    }
}