package com.example.splitthebill.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.splitthebill.R
import com.example.splitthebill.adapter.PessoaAdapter
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Pessoa

class MainActivity : BaseActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Data Source
    private val pessoaList: MutableList<Pessoa> = mutableListOf()

    //Adapter
    private val pessoaAdapter: PessoaAdapter by lazy {
        PessoaAdapter(
            this,
            pessoaList
        )
    }

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        supportActionBar?.subtitle = "Lista de pessoas"

        fillPessoaList()
        amb.pessoaLv.adapter = pessoaAdapter

        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val pessoa = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    result.data?.getParcelableExtra(EXTRA_PESSOA, Pessoa::class.java)
                }
                else{
                    result.data?.getParcelableExtra(EXTRA_PESSOA)
                }
                pessoa?.let {
                    pessoaList.add(it)
                    pessoaAdapter.notifyDataSetChanged()
                }

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addContactMi -> {
                carl.launch(Intent(this, PessoaActivity::class.java))
                true
            }
            R.id.racha -> {
                carl.launch(Intent(this, RachaActivity::class.java))
                true
            }
            else -> false
        }
    }




    //função para preencher nossa DS

    private fun fillPessoaList(){
        for(index in 1 .. 10){
            pessoaList.add(
                Pessoa(
                    index,
                    "Nome $index",
                    "Descrição dos ngredientes $index",
                    index.toDouble()
                )
            )
        }
    }
}