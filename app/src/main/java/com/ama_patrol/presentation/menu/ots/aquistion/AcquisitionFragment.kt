package com.ama_patrol.presentation.menu.ots.aquistion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.databinding.AquistionLayoutBinding
import com.ama_patrol.presentation.menu.HomeViewModel


class AcquisitionFragment : Fragment() {

    private var _binding: AquistionLayoutBinding? = null

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

        _binding = AquistionLayoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_acquisitionFragment_to_navigation_menu)
        }
        binding.btnVisit.setOnClickListener {
            findNavController().navigate(R.id.action_acquistionFragment_to_visit_acquisition_fragment)
        }
    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
