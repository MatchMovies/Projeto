package com.br.matchmovies.view


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.br.matchmovies.R
import com.br.matchmovies.databinding.ActivityMainLoginBinding
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("WrongViewCast")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btcadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        binding.btentrar.setOnClickListener {

            val email = binding.fielName.text.toString()
            val senha = binding.fielPass.text.toString()
            val mensagem_erro = binding.mensagemErro

            if(email.isEmpty() || senha.isEmpty()){
                mensagem_erro.setText("Preencha todos os campos!")

            }else{

                AutenticarUsuario()

            }
        }

    }

    private fun AutenticarUsuario(){

        val email = binding.fielName.text.toString()
        val senha = binding.fielPass.text.toString()
        val mensagem_erro = binding.mensagemErro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, "Login Efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                IrParaTelaHome()
            }
        }.addOnFailureListener {
            mensagem_erro.setText("Erro ao Logar Usuário")
        }

    }

    private fun IrParaTelaHome(){
        val intent = Intent(this,HomeActivity:: class.java)
        startActivity(intent)
        finish()



    }


}
