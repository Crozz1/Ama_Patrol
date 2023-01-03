package com.ama_patrol.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ama_patrol.MainActivity
import com.ama_patrol.RegisterActivity
import com.ama_patrol.databinding.LoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

         lateinit var auth : FirebaseAuth
     private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       /* setupViews()
        setupListeners()*/

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        with(binding) {
            tvRegister.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
            btnLogin.setOnClickListener {
                val  email = binding.edtUserName.text.toString()
                val  password = binding.edtPass.text.toString()

                //Validasi Email
                if (email.isEmpty()){
                    binding.edtUserName.error = "Email Harus Diisi !!"
                    binding.edtUserName.requestFocus()
                    return@setOnClickListener
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.edtPass.error = "Email Tidak  Valid !!"
                    binding.edtPass.requestFocus()
                    return@setOnClickListener
                }

                if (password.isEmpty()){
                    binding.edtPass.error = "Password Harus Diisi !!"
                    binding.edtPass.requestFocus()
                    return@setOnClickListener
                }
                LoginFirebase (email,password)
            }

        }
    }
    private fun LoginFirebase(email : String, password :String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val intent =Intent (this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}



//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()


