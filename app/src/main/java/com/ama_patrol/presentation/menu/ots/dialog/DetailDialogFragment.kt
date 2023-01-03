package com.ama_patrol.presentation.menu.ots.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ama_patrol.R
import com.ama_patrol.presentation.menu.ots.DialogFragment

class DetailDialogFragment : Fragment() {

    lateinit var btnShowDialog: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.form_maintenance1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //btnShowDialog = view.findViewById(R.id.btn_show_dialog)

        btnShowDialog.setOnClickListener {
            val mOptionDialogFragment = DialogFragment()

            val mFragmentManager = childFragmentManager
            mOptionDialogFragment.show(
                mFragmentManager,
                DialogFragment::class.java.simpleName
            )
        }
    }

    internal var optionDialogListener: DialogFragment.OnOptionDialogListener =
        object : DialogFragment.OnOptionDialogListener {
            override fun onOptionChosen(text: String?) {
                Toast.makeText(requireContext(), "Anda Memilih : " + text, Toast.LENGTH_SHORT).show()
            }
        }
}