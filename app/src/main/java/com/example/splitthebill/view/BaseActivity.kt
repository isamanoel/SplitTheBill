package com.example.splitthebill.view

import androidx.appcompat.app.AppCompatActivity

sealed class BaseActivity: AppCompatActivity(){
    protected final val EXTRA_PESSOA = "Pessoa"
    protected final val EXTRA_PESSOA_LIST = "PessoaList"
    protected final val REQUEST_DETALHES = 1
}