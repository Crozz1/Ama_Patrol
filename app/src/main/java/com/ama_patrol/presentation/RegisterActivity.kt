package com.ama_patrol

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ama_patrol.databinding.RegisterBinding
import com.ama_patrol.presentation.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {

    lateinit var  auth : FirebaseAuth
    private lateinit var binding: RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        with(binding) {
            tvSignin.setOnClickListener {
                val intent = Intent(this@RegisterActivity, LoginActivity ::class.java)
                startActivity(intent)
            }
            btnSigup.setOnClickListener {
                 val  email = binding.edtEmail.text.toString()
                 val  password = binding.edtPass.text.toString()

                //Validasi Email tidak sesuai
                if (email.isEmpty()){
                    binding.edtEmail.error = "Email Harus Diisi !!"
                    binding.edtEmail.requestFocus()
                    return@setOnClickListener
                }

                //Validasi Password
                if (password.isEmpty()){
                    binding.edtPass.error = "Password Harus Diisi !!"
                    binding.edtPass.requestFocus()
                    return@setOnClickListener
                }

                //Validasi panjang password
                if (email.length < 5){
                    binding.edtPass.error = "Password Minimal 6 Karakter"
                    binding.edtPass.requestFocus()
                    return@setOnClickListener
                }
                RegisterFirebase (email,password)
//
//                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
    }
    private fun RegisterFirebase(email : String, password :String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil",Toast.LENGTH_SHORT).show()
                    val intent =Intent (this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}




