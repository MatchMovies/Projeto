package com.br.matchmovies.fragments
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

import androidx.fragment.app.Fragment



import com.br.matchmovies.R
import com.br.matchmovies.imagecapture.PermissionsHelper
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import java.io.File
//
//class EditarCadastroFragment : Fragment() {
//
//    private var imageUri: Uri? = null
//    private val firebaseAuth = Firebase.auth
//    private val firebaseStorage = Firebase.storage
//    private var userAuth: FirebaseUser? = null
//
//    private val picture by lazy { view?.findViewById<ImageView>(R.id.picture) }
//
//    var fileShare: File? = null
//    private lateinit var permissionsHelper: PermissionsHelper
//
//    companion object {
//        const val FILE_AUTHORITY = "com.example.macthmovies"
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        permissionsHelper = PermissionsHelper(this)
//        userAuth = firebaseAuth.currentUser
//        getCurrentUserPicture()
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//        val currentUser = firebaseAuth.currentUser
//        //   CadastrarUsuario(currentUser?.email ?: "Usuário desconectado")
//    }
//
////    fun getuser(view: View) {
////        firebaseAuth.currentUser?.let { user ->
////            firestoreDb.collection("users")
////                .document(user.uid)
////                .get()
////                .addOnSuccessListener {
////                    val user = it.toObject(User::class.java)
////                    user.toString()
////                }.addOnFailureListener {
////                    it
////                }
////        }
////    }
//
//    private fun getCurrentUserPicture() {
//        userAuth?.let { user ->
//            firebaseStorage.getReference("uploads")
//                .child(user.uid)
//                .downloadUrl
//                .addOnSuccessListener { url ->
//                    Toast.makeText(requireContext(), "Imagem carregada com sucesso", Toast.LENGTH_LONG)
//                        .show()
//                    Picasso.get().load(url).into(picture)
//                }.addOnFailureListener {
//                    Toast.makeText(requireContext(), "Erro de downloading: ${it.message}", Toast.LENGTH_LONG)
//                        .show()
//                }
//        }
//    }
//
//    fun upload(view: View) {
//        imageUri?.let { image ->
//            userAuth?.let { user ->
//                firebaseStorage.getReference("uploads")
//                    .child(user.uid)
//                    .putFile(image)
//                    .addOnSuccessListener {
//                        Toast.makeText(requireContext(),
//                            "Imagem enviada com sucesso.",
//                            Toast.LENGTH_LONG
//                        )
//                            .show()
//                    }.addOnFailureListener {
//                        Toast.makeText(requireContext(),
//                            "Error uploadind: ${it.message}",
//                            Toast.LENGTH_LONG,
//                        )
//                            .show()
//                    }.addOnProgressListener {
//                        it
//                    }
//            }
//        }
//    }
//}
//


