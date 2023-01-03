package com.ama_patrol.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ama_patrol.databinding.ChangeEmailBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException


class ChangeEmailFragment : Fragment() {


//    private var _binding: ChangeEmailBinding? = null
//    private val binding get() = _binding!!
//    lateinit var auth: FirebaseAuth
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = ChangeEmailBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//        auth = FirebaseAuth.getInstance()
//        val user = auth.currentUser
//
//        binding.cvChangeEmailInputPass.visibility = View.VISIBLE
//        binding.cvChangeEmail.visibility = View.GONE
//
//        binding.btnNext.setOnClickListener {
//            var pass = binding.edtChangeEmailPassword.text.toString()
//
//            if (pass.isEmpty()) {
//                binding.edtChangeEmailPassword.error = "Password Harus Terisi"
//                binding.edtChangeEmailPassword.requestFocus()
//                return@setOnClickListener
//            }
//
//            // cek password
//            user.let {
//                val userCredential = EmailAuthProvider.getCredential(it?.email!!, pass)
//                it.reauthenticate(userCredential).addOnCompleteListener { task ->
//                    when {
//                        task.isSuccessful -> {
//                            binding.cvChangeEmailInputPass.visibility = View.GONE
//                            binding.cvChangeEmail.visibility = View.VISIBLE
//                        }
//                        task.exception is FirebaseAuthInvalidCredentialsException -> {
//                            binding.edtChangeEmailPassword.error = "Password Salah"
//                            binding.edtChangeEmailPassword.requestFocus()
//                        }
//                        else -> {
//                            Toast.makeText(
//                                requireContext(),
//                                "${task.exception?.message}",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                }
//            }
//        }
//    }
}


//        binding.btnChangeEmail.setOnClickListener newEmail@{
//            var newEmail = binding.edtChangeEmail.text.toString()
//
//            if (newEmail.isEmpty()) {
//                binding.edtChangeEmail.error = "Email Harus Terisi"
//                binding.edtChangeEmail.requestFocus()
//                return@newEmail
//            }
//
//            if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
//                binding.edtChangeEmail.error = "Email Tidak Valid"
//                binding.edtChangeEmail.requestFocus()
//                return@newEmail
//            }
//
//            user?.let {
//                user.updateEmail(newEmail).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        Toast.makeText(
//                            requireContext(),
//                            "Email Berhasil Diubah",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        val intent = Intent(this, UserFragment::class.java)
//                        startActivity(intent)
//                    } else {
//                        Toast.makeText(
//                            requireContext(),
//                            "${it.exception?.message}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            }
//
//        }
//    }


