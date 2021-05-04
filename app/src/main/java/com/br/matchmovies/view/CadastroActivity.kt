package com.br.matchmovies.view

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.br.matchmovies.R
import com.br.matchmovies.databinding.ActivityMainCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainCadastroBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btcadastro.setOnClickListener {

            val nome = binding.editNome.text.toString()
            val tel = binding.editNumerotelefone.text.toString()
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem_erro = binding.mensagemErro

            if (nome.isEmpty() || tel.isEmpty() || email.isEmpty() || senha.isEmpty()){
                mensagem_erro.setText("Preencha todos os campos")
            }else{
                CadastrarUsuario()
            }
        }

    }

    private fun CadastrarUsuario(){

        val nome = binding.editNome.text.toString()
        val tel = binding.editNumerotelefone.text.toString()
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem_erro = binding.mensagemErro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.editEmail.setText("")
                binding.editSenha.setText("")
                binding.editNome.setText("")
                binding.editNumerotelefone.setText("")
                mensagem_erro.setText("")
            }
        }.addOnFailureListener {

            var erro = it
            when{

                erro is FirebaseAuthWeakPasswordException -> mensagem_erro.setText("Digite uma senha com no mínimo 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> mensagem_erro.setText("E-mail já cadastrado")
                erro is FirebaseNetworkException -> mensagem_erro.setText("Sem conexão com a internet")
                else ->  mensagem_erro.setText("Erro ao cadastrar Usuário")
            }

        }

    }
}