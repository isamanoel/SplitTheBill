package com.example.splitthebill.view

import android.content.Intent
import android.os.Bundle
import com.example.splitthebill.databinding.ActivityPessoaBinding
import com.example.splitthebill.model.Pessoa
import java.util.*


class PessoaActivity : BaseActivity() {
    private val acb:ActivityPessoaBinding by lazy{
        ActivityPessoaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        supportActionBar?.subtitle = "Informações da pessoa"

        acb.saveBt.setOnClickListener{
            val pessoa: Pessoa = Pessoa(
                id = generateId(),
                name = acb.nameEt.text.toString(),
                desc = acb.descEt.text.toString(),
                value = acb.valueEt.text.toString().toDouble()
            )

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PESSOA, pessoa)
            setResult(RESULT_OK, resultIntent)
            finish()

        }
    }

    private fun generateId(): Int{
        val random = Random(System.currentTimeMillis())
        return random.nextInt()
    }
}