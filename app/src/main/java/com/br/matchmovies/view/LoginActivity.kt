package com.br.matchmovies.view


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.br.matchmovies.R
import com.br.matchmovies.databinding.ActivityMainLoginBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.internal.WebDialog
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forgot_password.*
import java.lang.Exception
import javax.security.auth.callback.Callback
import kotlin.math.sign

@SuppressLint("WrongViewCast")
class LoginActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainLoginBinding
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var callbackManager: CallbackManager
     var loginManager = LoginManager.getInstance()
    val button by lazy { findViewById<ImageView>(R.id.facebooklog) }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        callbackManager = CallbackManager.Factory.create()
        firebaseAuth = FirebaseAuth.getInstance()



        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().requestProfile().build()

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)

        binding.btesqueci.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

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

        button.setOnClickListener {
            signFacebook()
        }





    }



    fun sigIn(view: View){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent,200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode,resultCode, data)

        if(requestCode == 200){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val login = task.getResult(ApiException::class.java)
                firabaseAuthGoogle(login?.idToken!!)
            }catch (e:ApiException){
               Toast.makeText(this,"Erro logar a conta",Toast.LENGTH_LONG).show()
            }catch (e: Exception){
                Toast.makeText(this,"Erro",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firabaseAuthGoogle(idToken: String) {
        val  credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    fun signFacebook(){
        loginManager.logInWithReadPermissions(this, arrayListOf("email","public_profile"))
        loginManager.registerCallback(callbackManager, object: FacebookCallback <LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                result?.let { firebaseAuthfacebook(it.accessToken) }


            }

            override fun onCancel() {
                Toast.makeText(this@LoginActivity,"Erro logar a conta",Toast.LENGTH_LONG).show()

            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(this@LoginActivity,"Erro",Toast.LENGTH_LONG).show()


            }


        })
    }

    private fun firebaseAuthfacebook(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
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
