package com.ama_patrol.data.repository

import androidx.lifecycle.MutableLiveData
import com.ama_patrol.data.models.client.Assign
import com.google.firebase.database.*

class AssignRepository {

//    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("ModelAssign")
//
//    @Volatile private var INSTANCE : AssignRepository ?= null
//
//    fun getInstance() : AssignRepository{
//        return INSTANCE ?: synchronized(this){
//
//            val instance = AssignRepository()
//            INSTANCE = instance
//            instance
//        }
//
//
//    }
//
//
//    fun loadUsers(userList : MutableLiveData<List<Assign>>){
//
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                try {
//
//                    val _userList : List<Assign> = snapshot.children.map { dataSnapshot ->
//
//                        dataSnapshot.getValue(Assign::class.java)!!
//
//                    }
//
//                    userList.postValue(_userList)
//
//                }catch (e : Exception){
//
//
//                }
//
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//
//        })
//
//
//    }

}