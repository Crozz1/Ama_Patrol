package com.ama_patrol.presentation.menu.ots.maintenace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.databinding.FormMaintenance2Binding
import com.ama_patrol.presentation.menu.HomeViewModel

class FormMaintenance2 : Fragment() {

    private var _binding: FormMaintenance2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FormMaintenance2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_formMaintenance2_to_formMaintenace1)
        }
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_formMaintenance2_to_formMaintenance3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
