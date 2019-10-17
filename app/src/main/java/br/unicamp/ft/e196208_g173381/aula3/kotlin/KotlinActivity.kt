package br.unicamp.ft.e196208_g173381.aula3.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.unicamp.ft.e196208_g173381.aula3.R
import kotlinx.android.synthetic.main.activity_kotlin.*
import java.util.*

class KotlinActivity : AppCompatActivity() {

    private lateinit var memoria: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        button2Pull.setOnClickListener {
           textViewColar.text = memoria
        }

        buttonPush.setOnClickListener {
            memoria = editTextCopiar.text.toString()
        }

        if (savedInstanceState != null){
            memoria = savedInstanceState.getString("chave","")
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("chave", memoria)
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}
