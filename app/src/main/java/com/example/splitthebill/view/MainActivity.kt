package com.example.splitthebill.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            //this,
            //pessoaList
        )
    }

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        supportActionBar?.subtitle = getString(R.string.pessoa_list)

        //fillContactList()
        amb.pessoaLv.adapter = pessoaAdapter

        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val contact = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    result.data?.getParcelableExtra(EXTRA_PESSOA, Pessoa::class.java)
                }
                else{
                    result.data?.getParcelableExtra(EXTRA_PESSOA)
                }
                contact?.let {
                    pessoaList.add(it)
                    pessoaAdapter.notifyDataSetChanged()
                }

            }

        }
    }

}

    /*




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addContactMi -> {
                carl.launch(Intent(this, contactActivity::class.java))
                true
            }
            else -> false
        }
    }

    //função para preencher nossa DS

    private fun fillContactList(){
        for(index in 1 .. 50){
            contactList.add(
                Contact(
                    index,
                    "Nome $index",
                    "Endereço $index",
                    "Telefone $index",
                    "Email $index"
                )
            )
        }
    }
}*/