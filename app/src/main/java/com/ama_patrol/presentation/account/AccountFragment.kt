package com.ama_patrol.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.btnData.setOnClickListener {
            findNavController().navigate(R.id.action_account_fragment_to_accountDetailFragment)
        }
        binding.resetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_account_fragment_to_ubahPassFragment)
        }
        binding.btnData.setOnClickListener {
            findNavController().navigate(R.id.action_account_fragment_to_accountDetailFragment)
        }
        binding.terms.setOnClickListener {
            findNavController().navigate(R.id.action_account_fragment_to_TOSFragment)
        }
        binding.btnChangeEmail.setOnClickListener {
            findNavController().navigate(R.id.action_account_fragment_to_changeEmailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}