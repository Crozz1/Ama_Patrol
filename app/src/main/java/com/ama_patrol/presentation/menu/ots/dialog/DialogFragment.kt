package com.ama_patrol.presentation.menu.ots

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.ama_patrol.R
import com.ama_patrol.databinding.DialogAlertBinding


class DialogFragment: DialogFragment()  {
 lateinit var btnChoose: Button
 lateinit var btnClose: Button
 lateinit var rgOptions: RadioGroup
 lateinit var rbBr: RadioButton
 lateinit var rbPoc: RadioButton
 lateinit var rbRang: RadioButton
 lateinit var rbHag: RadioButton
private var optionDialogListener: OnOptionDialogListener? = null

private var _binding: DialogAlertBinding? = null

private val binding get() = _binding!!


override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {

    _binding = DialogAlertBinding.inflate(inflater, container, false)
    return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnChoose = view.findViewById(R.id.btn_choose)
    btnClose = view.findViewById(R.id.btn_close)
    rgOptions = view.findViewById(R.id.rg_options)
    rbBr = view.findViewById(R.id.rb_br)
    rbPoc = view.findViewById(R.id.rb_poc)
    rbRang = view.findViewById(R.id.rb_rang)
    rbHag = view.findViewById(R.id.rb_hag)

    btnChoose.setOnClickListener {
        val checkedRadioButtonId = rgOptions.checkedRadioButtonId
        if (checkedRadioButtonId != -1) {
            var coach: String? = null
            when (checkedRadioButtonId) {
                R.id.rb_br -> coach = rbBr.text.toString().trim()
                R.id.rb_poc -> coach = rbPoc.text.toString().trim()
                R.id.rb_rang -> coach = rbRang.text.toString().trim()
                R.id.rb_hag -> coach = rbHag.toString().trim()
            }
            optionDialogListener?.onOptionChosen(coach)
            dialog?.dismiss()
        }
    }
    btnClose.setOnClickListener {
        dialog?.cancel()
    }
}

override fun onAttach(context: Context) {
    super.onAttach(context)
    val fragment = parentFragment
    if (fragment is DialogFragment) {
        this.optionDialogListener
    }
}

override fun onDetach() {
    super.onDetach()
    this.optionDialogListener = null
}

interface OnOptionDialogListener {
    fun onOptionChosen(text: String?)
}

}
