package com.ama_patrol.presentation.menu.manage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ama_patrol.R
import com.ama_patrol.data.models.client.Assign


@Suppress("DEPRECATION")
class KelolaanAdapter (private val MainList: ArrayList<Assign>) :
    RecyclerView.Adapter<KelolaanAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMain = MainList[position]
        holder.Nagen.text = currentMain.nAgen
        holder.Tgl.text = currentMain.tgl
        holder.Nama.text = currentMain.nAma
    }

    override fun getItemCount(): Int {
        return MainList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val Nagen : TextView = itemView.findViewById(R.id.namabni)
        val Tgl : TextView = itemView.findViewById(R.id.tanggal)
        val Nama : TextView = itemView.findViewById(R.id.ama)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}

//class KelolaanAdapter (private val context: Context,
//                       private val daftarKelola: ArrayList<Assign>) : RecyclerView.Adapter<MainViewModel>() {
//    private val listener: FirebaseDataListener
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewModel {
//
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
//        return MainViewModel(view)
//    }
//
//    override fun onBindViewHolder(holder: MainViewModel, position: Int) {
////        val generator = ColorGenerator.MATERIAL //custom color
////        val color = generator.randomColor
////        holder.view.setCardBackgroundColor(color)
//        holder.namaAgen.text = "Nama Agen   : " + (daftarKelola.get(position).nAgen)
//        holder.tanggal.text = "Tanggal     : " + daftarKelola.get(position).tgl
//        holder.namaAma.text = "Nama Ama   : " + daftarKelola.get(position).nAma
//        holder.view.setOnClickListener { listener.onDataClick(daftarKelola.get(position), position) }
//    }
//
//    override fun getItemCount(): Int {
//
//        return daftarKelola.size
//    }
//
//    //interface data listener
//    interface FirebaseDataListener {
//        fun onDataClick(kelola: Assign, position: Int)
//    }
//
//    init {
//        listener = context as FirebaseDataListener
//    }
//}





























//class KelolaanAdapter (private val assignList : ArrayList<Assign>) : RecyclerView.Adapter<KelolaanAdapter.MyViewHolder>() {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list,
//            parent,false)
//        return MyViewHolder(itemView)
//
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//        val currentitem = assignList[position]
//
//        holder.Nagen.text = currentitem.nAgen
//        holder.Tgl.text = currentitem.tgl
//        holder.Nama.text = currentitem.nAma
//
//    }
//
//    override fun getItemCount(): Int {
//
//        return assignList.size
//    }
//
//
//    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//
//        val Nagen : TextView = itemView.findViewById(R.id.namabni)
//        val Tgl : TextView = itemView.findViewById(R.id.tanggal)
//        val Nama : TextView = itemView.findViewById(R.id.ama)
//
//    }
//
//}

