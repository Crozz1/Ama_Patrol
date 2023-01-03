package com.ama_patrol.presentation.menu.manage

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ama_patrol.R
import com.ama_patrol.data.models.client.Assign
import com.ama_patrol.databinding.KelolaanLayoutBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*


class KelolaanFragment :Fragment() {

    private lateinit var MainRecyclerView: RecyclerView
    //private lateinit var tvLoadingData: TextView
    private lateinit var MainList: ArrayList<Assign>
    private lateinit var db: DatabaseReference

    private var _binding: KelolaanLayoutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = KelolaanLayoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_kelolaan_fragment_to_navigation_menu)
        }
        binding.btnTodo.setOnClickListener {
            findNavController().navigate(R.id.action_kelolaan_fragment_to_maintenance_fragment)
        }
        binding.fabFiredb.setOnClickListener {
            findNavController().navigate(R.id.action_kelolaan_fragment_to_updateKelolaanFragment)
        }
        MainRecyclerView = root.findViewById(R.id.rv_firedb)
        MainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        MainRecyclerView.setHasFixedSize(true)
       // tvLoadingData = findViewById(R.id.tvLoadingData)

        MainList = arrayListOf<Assign>()

        getKelolaData()
        return root
    }

    private fun getKelolaData() {

        MainRecyclerView.visibility = View.GONE
        //tvLoadingData.visibility = View.VISIBLE

        db= FirebaseDatabase.getInstance().getReference("assigns")

        db.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                MainList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(Assign::class.java)
                        MainList.add(empData!!)
                    }
                    val mAdapter = KelolaanAdapter(MainList)
                    MainRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : KelolaanAdapter.onItemClickListener{
                        @SuppressLint("SuspiciousIndentation")
                        override fun onItemClick(position: Int) {

                            val intent = Intent(requireContext(), AddEditFragment::class.java)

                            //Put extras

                                intent.putExtra("AgenName", MainList[position].nAgen)
                                intent.putExtra("Tanggal", MainList[position].tgl)
                                intent.putExtra("Ama", MainList[position].nAma)
                                startActivity(intent)
                            }

                    })

                    MainRecyclerView.visibility = View.VISIBLE
                  //  tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    }






















//class KelolaanFragment :Fragment(), KelolaanAdapter.FirebaseDataListener {
//    lateinit var  mFloatingActionButton: ExtendedFloatingActionButton
//    lateinit var  mEditNagen: EditText
//    lateinit var  mEdittgl: EditText
//    lateinit var  mEditnAma: EditText
//    lateinit var  mbtnsave :Button
//    lateinit var  mRecyclerView: RecyclerView
//    lateinit var  mAdapter: KelolaanAdapter
//    lateinit var  daftarKelola: ArrayList<Assign>
//    lateinit var  mDatabaseReference: DatabaseReference
//    lateinit var  mFirebaseInstance: FirebaseDatabase
//
//    private var _binding: KelolaanLayoutBinding? = null
//
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = KelolaanLayoutBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        return root
//    }
////        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21){
////            MainActivity.Companion.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
////        }
////        if (Build.VERSION.SDK_INT >= 19){
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
////                getWindow().getDecorView()
////                    .setSystemUiVisibility((android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
////                            or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
////                            or android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR))
////            }
////        }
////        if (Build.VERSION.SDK_INT >= 21){
////            MainActivity.Companion.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
////            getWindow().setStatusBarColor(Color.TRANSPARENT)
////        }
//  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//        mRecyclerView.setHasFixedSize(true)
//        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        FirebaseApp.initializeApp(requireContext())
//        mFirebaseInstance = FirebaseDatabase.getInstance()
//        mDatabaseReference = mFirebaseInstance.getReference("kelola")
//        mDatabaseReference.child("data_kelola").addValueEventListener(object : ValueEventListener{
//          override  fun onDataChange(dataSnapshot: DataSnapshot){
//                daftarKelola = ArrayList()
//                for (  mDataSnapshot: DataSnapshot in dataSnapshot.children) {
//                      val kelola: Assign? = mDataSnapshot.getValue(Assign::class.java)
//                      val idAgen: String = mDatabaseReference.push().key
//                    !!
//                    if (kelola != null) {
//                        daftarKelola.add(kelola)
//                    }
//                }
//                mAdapter = KelolaanAdapter(requireContext(), daftarKelola)
//              mRecyclerView.adapter = mAdapter
//            }
//            override  fun onCancelled(databaseError: DatabaseError){
//                Toast.makeText(requireContext(),
//                    databaseError.details + " " + databaseError.message, Toast.LENGTH_LONG).show()
//            }
//        })
//        mFloatingActionButton = view.findViewById(R.id.fab_firedb)
//        mFloatingActionButton.setOnClickListener { dialogTambahBarang() }
//}
//    override fun onDataClick(kelola: Assign, position:Int){
//        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
//        builder.setTitle("Pilih Aksi")
//        builder.setPositiveButton("UPDATE"
//        ) { dialog, id -> dialogUpdateBarang(kelola) }
//        builder.setNegativeButton("HAPUS"
//        ) { dialog, id -> hapusDataBarang(kelola) }
//        builder.setNeutralButton("BATAL"
//        ) { dialog, id -> dialog.dismiss() }
//        val dialog: Dialog = builder.create()
//        dialog.show()
//    }
//    private   fun dialogTambahBarang(){
//        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
//        builder.setTitle("Tambah Data Kelola")
//        val view:View = layoutInflater.inflate(R.layout.cru_kelolaan, null)
//        mEditNagen = view.findViewById(R.id.et_nagen)
//        mEdittgl = view.findViewById(R.id.et_tgl)
//        mEditnAma = view.findViewById(R.id.et_nAma)
////            builder.setView(view)
//        builder.setPositiveButton("SIMPAN"
//        ) { dialog, id ->
//            val namaAgen: String = mEditNagen.text.toString()
//            val tanggal: String = mEdittgl.text.toString()
//            val namaAma: String = mEditnAma.text.toString()
//            if (!namaAgen.isEmpty() && !tanggal.isEmpty() && !namaAma.isEmpty()) {
//                submitDataKelola(Assign(namaAgen, tanggal, namaAma))
//            } else {
//                Toast.makeText(requireContext(), "Data harus di isi!", Toast.LENGTH_LONG).show()
//            }
//        }
//        builder.setNegativeButton("BATAL"
//        ) { dialog, id -> dialog.dismiss() }
//        val dialog: Dialog = builder.create()
//        dialog.show()
//    }
//    private   fun dialogUpdateBarang(kelola: Assign?){
//        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
//        builder.setTitle("Edit Data ModelBarang")
//        val view: View = layoutInflater.inflate(R.layout.cru_kelolaan, null)
//        mEditNagen = view.findViewById(R.id.et_nagen)
//        mEdittgl = view.findViewById(R.id.et_tgl)
//        mEditnAma = view.findViewById(R.id.et_nAma)
//        mEditNagen.setText(kelola.getNamaAgen())
//        mEdittgl.setText(kelola.gettanggal())
//        mEditnAma.setText(kelola.getNamaAma())
//        builder.setView(view)
//        if (kelola != null){
//            builder.setPositiveButton("SIMPAN"
//            ) { dialog, id ->
//                kelola.setNama(mEditNagen.text.toString())
//                kelola.setMerk(mEdittgl.text.toString())
//                kelola.setHarga(mEditnAma.text.toString())
//                updateDataBarang(kelola)
//            }
//        }
//        builder.setNegativeButton("BATAL"
//        ) { dialog, id -> dialog.dismiss() }
//        val dialog: Dialog = builder.create()
//        dialog.show()
//    }
//    private   fun submitDataKelola(kelola: Assign){
//        mDatabaseReference.child("data_kelola").push()
//            .setValue(kelola).addOnSuccessListener {
//                Toast.makeText(
//                    requireContext(),
//                    "Data barang berhasil di simpan !",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//    }
//    private   fun updateDataBarang(kelola: Assign?){
//        mDatabaseReference.child("data_kelola").child(kelola.toString())
//            .setValue(kelola).addOnSuccessListener {
//                Toast.makeText(
//                    requireContext(),
//                    "Data berhasil di update !",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//    }
//    private   fun hapusDataBarang(kelola: Assign){
//        kelola.idAgen?.let {
//            mDatabaseReference.child("data_kelola").child(it)
//                .removeValue().addOnSuccessListener {
//                    Toast.makeText(
//                        requireContext(),
//                        "Data berhasil di hapus !",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//        }
//    }
////    companion object  {
////        fun setWindowFlag(activity: Activity, bits: kotlin.Int, on: kotlin.Boolean){
////            val win: Window = activity.getWindow()
////            val winParams: WindowManager.LayoutParams = win.getAttributes()
////            if (on){
////                winParams.flags = winParams.flags or bits
////            } else {
////                winParams.flags = winParams.flags and bits.inv()
////            }
////            win.setAttributes(winParams)
////        }
////    }































