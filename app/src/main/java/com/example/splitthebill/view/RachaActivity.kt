package com.example.splitthebill.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.splitthebill.databinding.ActivityRachaBinding
import com.example.splitthebill.model.Pessoa

class RachaActivity : BaseActivity() {
    private val binding: ActivityRachaBinding by lazy {
        ActivityRachaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pessoaList = intent.getParcelableArrayListExtra<Pessoa>(EXTRA_PESSOA_LIST)

        // Aqui você pode realizar as alterações necessárias na lista de pessoas
        // e gerar o texto desejado para exibir no infoTv

        val infoText = generateInfoText(pessoaList)
        binding.infoTv.text = infoText
    }

    private fun generateInfoText(pessoaList: List<Pessoa>?): String {
        // Aqui você pode implementar a lógica para gerar o texto a partir da lista de pessoas
        // e retornar o resultado final

        return "Texto gerado com base na lista de pessoas"
    }
}

