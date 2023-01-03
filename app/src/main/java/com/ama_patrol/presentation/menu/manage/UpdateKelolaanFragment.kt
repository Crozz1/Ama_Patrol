package com.ama_patrol.presentation.menu.manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ama_patrol.R
import com.ama_patrol.data.models.client.Assign
import com.ama_patrol.databinding.CruKelolaanBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class UpdateKelolaanFragment: Fragment() {
}

//
////    private lateinit var etid: EditText
////    private lateinit var etNagen: EditText
////    private lateinit var etAlamat: EditText
////    private lateinit var ettgl: EditText
////    private lateinit var etAma: EditText
////    private lateinit var btnSaveData: Button
//
//    private var _binding: CruKelolaanBinding? = null
//
//    private val binding get() = _binding!!
//    private lateinit var db: DatabaseReference
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = CruKelolaanBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        return root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        etid = view.findViewById(R.id.et_id)
//        etNagen = view.findViewById(R.id.et_nagen)
//        ettgl = view.findViewById(R.id.et_tgl)
//        etAlamat = view.findViewById(R.id.et_alamat)
//        etAma = view.findViewById(R.id.et_nAma)
//        btnSaveData = view.findViewById(R.id.btn_save)
//
//        db = FirebaseDatabase.getInstance().getReference("assigns")
//
//        btnSaveData.setOnClickListener {
//            saveAssign()
//        }
//    }
//
//    private fun saveAssign() {
//        //getting values
//        val idAgen = etid.text.toString()
//        val Nagen = etNagen.text.toString()
//        val tgl = ettgl.text.toString()
//        val alamat = etAlamat.text.toString()
//        val nAma = etAlamat.text.toString()
//
//        if (idAgen.isEmpty()) {
//            etid.error = "Please enter ID Agen"
//        }
//        if (Nagen.isEmpty()) {
//           etNagen .error = "Please enter age"
//        }
//        if (tgl.isEmpty()) {
//            ettgl.error = "Please enter salary"
//        }
//        if (alamat.isEmpty()) {
//            etAlamat.error = "Please enter salary"
//        }
//        if (nAma.isEmpty()) {
//            etAma.error = "Please enter salary"
//        }
//
//        val id = db.push().key!!
//
//        val kelola = Assign(id,idAgen,Nagen,tgl,alamat,nAma)
//
//
//        db.child(id).setValue(kelola)
//            .addOnCompleteListener {
//                Toast.makeText(requireContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show()
//
//                etid.text.clear()
//                etNagen.text.clear()
//                ettgl.text.clear()
//                etAlamat.text.clear()
//                etAma.text.clear()
//
//            }.addOnFailureListener { err ->
//                Toast.makeText(requireContext(), "Error ${err.message}", Toast.LENGTH_LONG).show()
//            }
//    }
//}
//
//
////            database = FirebaseDatabase.getInstance().getReference("assigns")
//////            val Assign = Assign(nAgen,tgl,nAma)
////            database.child(tgl).setValue(Assign).addOnSuccessListener {
////
////                binding.etNagen.text?.clear()
////                binding.etTgl.text?.clear()
////                binding.etNAma.text?.clear()
////
////
////                Toast.makeText(requireContext(),"Successfully Saved",Toast.LENGTH_SHORT).show()
////
////            }.addOnFailureListener{
////
////                Toast.makeText(requireContext(),"Failed",Toast.LENGTH_SHORT).show()
////
////
////            }
////
////
////        }
////
////    }
////}
