package com.ama_patrol.presentation.account

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ama_patrol.presentation.LoginActivity
import com.ama_patrol.databinding.ResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth


class ResetPasswordFragment : Fragment() {
//
//    private var _binding: ResetPasswordBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = ResetPasswordBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        return root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    binding.btnReset.setOnClickListener {
//            val email = binding.edtEmailReset.text.toString()
//            val edtEmail = binding.edtEmailReset
//
//            if (email.isEmpty()) {
//                edtEmail.error = "Email Tidak Boleh Kosong"
//                edtEmail.requestFocus()
//                return@setOnClickListener
//            }
//
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                edtEmail.error = "Email Tidak Valid"
//                edtEmail.requestFocus()
//                return@setOnClickListener
//            }
//
//            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
//
//                if (it.isSuccessful){
//                    Toast.makeText(requireContext(), "Email Reset Password Telah Dikirim", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(activity, AccountFragment::class.java)
//                    startActivity(intent)
//                //    val intent = Intent((getthis@ResetPasswordFragment, LoginActivity::class.java)
////                    startActivity(intent)
//
//
//                } else {
//                    Toast.makeText(requireContext(), "${it.exception?.message}", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//
//        }
    }
