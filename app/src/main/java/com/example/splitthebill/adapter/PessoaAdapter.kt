package com.example.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.databinding.TilePessoaBinding
import com.example.splitthebill.model.Pessoa

class PessoaAdapter (
    context: Context,
    private val pessoaList: MutableList<Pessoa>
): ArrayAdapter<Pessoa>(context, R.layout.tile_pessoa, pessoaList) {
    private lateinit var tcb: TilePessoaBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val pessoa: Pessoa = pessoaList[position]

        var tileContactView = convertView
        if (tileContactView == null) {
            //infla uma nova célula
            tcb = TilePessoaBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater, parent, false
            )
            tileContactView = tcb.root
        }

        tileContactView.findViewById<TextView>(R.id.nameTv).text = pessoa.name
        tileContactView.findViewById<TextView>(R.id.valueTv).text = pessoa.value.toString()

        return tileContactView
    }
}