package com.ama_patrol.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.databinding.MenuListBinding


class MenuFragment : Fragment() {

    private var _binding: MenuListBinding? = null

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

        _binding = MenuListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_menu_to_loginActivity)
        }
        binding.ots.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_menu_to_ots_fragment)
        }
        binding.kelolaan.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_menu_to_kelolaan_fragment)
        }
        binding.locator.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_menu_to_locatorFragment)
        }
        binding.learning.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_menu_to_learningFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
