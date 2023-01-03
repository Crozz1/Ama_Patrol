package com.ama_patrol.presentation.menu.manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.data.models.client.Assign
import com.ama_patrol.databinding.CruKelolaanBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.cru_kelolaan.*
import kotlinx.android.synthetic.main.cru_kelolaan.edt_nik
import kotlinx.android.synthetic.main.home_boarding.*


class AddEditFragment : Fragment() {

    private var _binding: CruKelolaanBinding? = null
    private val binding get() = _binding!!
    private var database: DatabaseReference? = null

    private var idagen: EditText? = null
    private var owner: EditText? = null
    private var namagen: EditText? = null
    private var nik: EditText? = null

    //    private lateinit var ettgl: EditText
    private var nope: EditText? = null
    private var alamat: EditText? = null
    private var Lusaha: EditText? = null
    private var Petugas: EditText? = null
    private lateinit var btnSave: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CruKelolaanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnAmbilGambar.setOnClickListener {
            findNavController().navigate(R.id.action_addEditFragment_to_takePictureFragment)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        namagen = view.findViewById(R.id.edt_nama_calon_agen)
        owner = view.findViewById(R.id.edt_owner)
        idagen = view.findViewById(R.id.edt_no_regist)
        nik = view.findViewById(R.id.edt_nik)
        nope = view.findViewById(R.id.edt_no)
        alamat = view.findViewById(R.id.edt_adress)
        Lusaha = view.findViewById(R.id.edt_lokasi)
        Petugas = view.findViewById(R.id.edt_petugas)
        btnSave = view.findViewById(R.id.btn_save)

        database = FirebaseDatabase.getInstance().getReference("assign")

        btnSave.setOnClickListener {
            saveDataKelola()
        }
    }

    private fun saveDataKelola() {
        //getting values
        val idAgen = edt_no_regist.text.toString()
        val Owner = edt_owner.text.toString()
        val nAgen = edt_nama_calon_agen.text.toString()
        val Nope = edt_no.text.toString()
        val Nik = edt_nik.text.toString()
        val Alamat = edt_adress.text.toString()
        val lUsaha = edt_lokasi.text.toString()
        val petugas = edt_petugas.text.toString()


        if (idAgen.isEmpty()) {
            idagen!!.error = "Field Harus Diisi"
        }
        if (Owner.isEmpty()) {
            owner!!.error = "Field Harus Diisi"
        }
        if (nAgen.isEmpty()) {
            namagen!!.error = "Field Harus Diisi"
        }
        if (Nik.isEmpty()) {
            nik!!.error = "Field Harus Diisi"
        }
        if (Nope.isEmpty()) {
            nope!!.error = "Field Harus Diisi"
        }
        if (Alamat.isEmpty()) {
            alamat!!.error = "Field Harus Diisi"
        }

        if (lUsaha.isEmpty()) {
            Lusaha!!.error = "Field Harus Diisi"
        }
        if (petugas.isEmpty()) {
            Petugas!!.error = "Field Harus Diisi"
        }

        val id = database?.push()?.key!!

        val kelola = Assign(id, idAgen, Owner, nAgen, Nik, Nope, Alamat, lUsaha, petugas)

        database!!.child(id).setValue(kelola)
            .addOnCompleteListener {
                Toast.makeText(requireContext(), "Data Berhasil Dimasukkan", Toast.LENGTH_LONG)
                    .show()

                idagen!!.text.clear()
                owner!!.text.clear()
                namagen!!.text.clear()
                nik!!.text.clear()
                nope!!.text.clear()
                alamat!!.text.clear()
                Lusaha!!.text.clear()
                Petugas!!.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(requireContext(), "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}
