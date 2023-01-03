package com.ama_patrol.presentation.menu.manage

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ama_patrol.R


class MainViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @JvmField
    var namaAgen: TextView
    @JvmField
    var tanggal: TextView
    @JvmField
    var namaAma: TextView
    @JvmField
    var view: CardView
    init {
        namaAgen = itemView.findViewById(R.id.namabni)
        tanggal = itemView.findViewById(R.id.tanggal)
        namaAma = itemView.findViewById(R.id.ama)
        view = itemView.findViewById(R.id.card)
    }
}