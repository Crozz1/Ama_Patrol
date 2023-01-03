package com.ama_patrol.presentation.menu.ots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.databinding.OtsLayoutBinding


class OtsFragment : Fragment() {

    private var _binding: OtsLayoutBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = OtsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAquistion.setOnClickListener {
            findNavController().navigate(R.id.action_ots_fragment_to_acquisition_fragment)
        }
        binding.btnMaintenance.setOnClickListener {
            findNavController().navigate(R.id.action_otsFragment_to_maintenance_fragment)
        }

    }
}

//private var _binding: ActivitytabBinding? = null
//
//private val binding get() = _binding!!
//
//
//override fun onCreateView(
//    inflater: LayoutInflater,
//    container: ViewGroup?,
//    savedInstanceState: Bundle?
//): View {
//
//    _binding = ActivitytabBinding.inflate(inflater, container, false)
//    return binding.root
//}
//
//override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//
//    val sectionsPagerAdapter = SectionsPagerAdapter(this,)
//    val viewPager: ViewPager = binding.viewPager
//    viewPager.adapter = sectionsPagerAdapter
//    val tabs: TabLayout = binding.tabs
//    tabs.setupWithViewPager(viewPager)
//    val fab: FloatingActionButton = binding.fab
//
//    fab.setOnClickListener { view ->
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show()
//    }
//}
//}

