package com.example.splitthebill.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.splitthebill.adapter.PessoaAdapter
import com.example.splitthebill.databinding.ActivityDetalhesBinding
import com.example.splitthebill.model.Pessoa

class DetalhesActivity : BaseActivity() {
    private lateinit var adb: ActivityDetalhesBinding
    private lateinit var pessoa: Pessoa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adb = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(adb.root)

        // Obtenha os dados da pessoa da Intent
        pessoa = intent.getParcelableExtra("EXTRA_PESSOA")!!

        // Exiba os dados da pessoa nos elementos de exibição
        adb.nameEt.text = pessoa.name.toEditable()
        adb.descEt.text = pessoa.desc.toEditable()
        adb.valueEt.text = pessoa.value.toString().toEditable()

        supportActionBar?.subtitle = "Detalhes da pessoa"

        adb.deleteBt.setOnClickListener {
            // Lógica para remover a pessoa da lista
            val pessoaList = intent.getParcelableArrayListExtra<Pessoa>("EXTRA_PESSOA_LIST")
            pessoaList?.remove(pessoa)
            println(pessoaList)

            val resultIntent = Intent().apply {
                putParcelableArrayListExtra("EXTRA_PESSOA_LIST", pessoaList)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        adb.editBt.setOnClickListener {
            // Lógica para editar a pessoa
            pessoa.name = adb.nameEt.text.toString()
            pessoa.desc = adb.descEt.text.toString()
            pessoa.value = adb.valueEt.text.toString().toDouble()

            // Atualize a pessoa na lista de pessoas
            val pessoaList = intent.getParcelableArrayListExtra<Pessoa>("EXTRA_PESSOA_LIST")
            pessoaList?.let {
                val index = it.indexOf(pessoa)
                if (index != -1) {
                    it[index] = pessoa
                    // Atualize a lista de pessoas no Intent
                    intent.putParcelableArrayListExtra("EXTRA_PESSOA_LIST", pessoaList)
                    setResult(Activity.RESULT_OK, intent)
                    Toast.makeText(this, "Pessoa atualizada com sucesso", Toast.LENGTH_SHORT).show()
                }
            }
            finish()
        }
    }

    private fun String.toEditable(): Editable {
        return Editable.Factory.getInstance().newEditable(this)
    }
}