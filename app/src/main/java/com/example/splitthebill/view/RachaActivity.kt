package com.example.splitthebill.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
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

        val pessoaList = intent.getParcelableArrayListExtra<Pessoa>(EXTRA_PESSOA_LIST) ?: arrayListOf()

        val infoText = generateInfoText(pessoaList)
        binding.infoTv.text = infoText
    }

    private fun generateInfoText(pessoaList: List<Pessoa>): SpannableStringBuilder {
        val media = pessoaList.sumOf { it.value } / pessoaList.size

        return pessoaList.fold(SpannableStringBuilder()) { texto, pessoa ->
            val diferenca = pessoa.value - media
            val status = when {
                diferenca > 0 -> "Receber"
                diferenca < 0 -> "Pagar"
                else -> "Nada a pagar"
            }

            val valorText = if (diferenca != 0.0) "R$ %.2f".format(Math.abs(diferenca)) else ""
            val corText = when {
                diferenca > 0 -> Color.GREEN
                diferenca < 0 -> Color.RED
                else -> Color.GRAY
            }

            val linhaText = "${pessoa.name} - $status $valorText\n"
            val spannableString = SpannableString(linhaText)
            spannableString.setSpan(ForegroundColorSpan(corText), 0, linhaText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            texto.append(spannableString)
        }
    }


}

