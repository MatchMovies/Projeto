package com.br.matchmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.br.matchmovies.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var emailEt : EditText
    lateinit var recuperarSenha : Button
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)


        firebaseAuth = FirebaseAuth.getInstance()


        emailEt = findViewById(R.id.editTextTextEmailAddress)
        recuperarSenha= findViewById(R.id.recuperar_senha_button)


        recuperarSenha.setOnClickListener {
            var email: String = emailEt.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Por favor digite seu E-mail.", Toast.LENGTH_LONG).show()
            } else {
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(this, OnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                        this,
                                        "Link para a troca de senha enviado para o seu E-mail.",
                                        Toast.LENGTH_LONG).show()
                                IrParaTelaLogin()
                            } else {
                                Toast.makeText(this, "Falha ao enviar E-mail.", Toast.LENGTH_LONG)
                                        .show()
                            }
                        })
            }
        }

        // setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.logo_toolbar)

    }

    private fun IrParaTelaLogin(){
        val intent = Intent(this,LoginActivity:: class.java)
        startActivity(intent)
    }
}