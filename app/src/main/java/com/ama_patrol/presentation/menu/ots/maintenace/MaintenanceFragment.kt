package com.ama_patrol.presentation.menu.ots.maintenace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.databinding.MaintenanceLayoutBinding

class MaintenanceFragment : Fragment() {

    private var _binding: MaintenanceLayoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MaintenanceLayoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_maintenance_fragment_to_navigation_menu)
        }
        binding.btnVisit.setOnClickListener {
            findNavController().navigate(R.id.action_maintenance_fragment_to_visitMaintenanceFragment)
        }
    }
           override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
