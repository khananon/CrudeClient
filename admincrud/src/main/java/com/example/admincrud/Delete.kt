package com.example.admincrud

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admincrud.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Delete : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete)
        binding=ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val uid : String= binding.SearcheditText.text.toString()
            if(uid.isNotEmpty()) {
                Search(uid)
            }else{
                Toast.makeText(this, "Enter the UID", Toast.LENGTH_SHORT).show()
            }
        }
        binding.DeleteButton.setOnClickListener {
            val uid:String=binding.UidShow.text.toString()
            if(uid.isNotEmpty()) {
                delete(uid)
            }else{
                Toast.makeText(this, "Error occur ", Toast.LENGTH_SHORT).show()
            }
        }


        }
    private fun Search(Uid:String){
         databaseReference=FirebaseDatabase.getInstance().getReference("Student info")
         databaseReference.child(Uid).get().addOnSuccessListener {
             if(it.exists()){
                 val uid=it.child("uid").value
                 val name=it.child("studentName").value
                 val Class=it.child("class").value
                 binding.SearcheditText.text.clear()
                 binding.UidShow.text=uid.toString()
                 binding.NameShow.text=name.toString()
                 binding.ClassShow.text=Class.toString()

             }else{
                 Toast.makeText(this, "Student  not found", Toast.LENGTH_SHORT).show()

             }
         }.addOnFailureListener {
             Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
         }

     }
   private fun delete(Uid: String){
       databaseReference=FirebaseDatabase.getInstance().getReference("Student info")
       databaseReference.child(Uid).removeValue().addOnSuccessListener {
           binding.UidShow.text=""
           binding.NameShow.text=""
           binding.ClassShow.text=""
           Toast.makeText(this, "Student $Uid Data delete", Toast.LENGTH_SHORT).show()
       }.addOnFailureListener {
           Toast.makeText(this, "Somthing went wrong", Toast.LENGTH_SHORT).show()
       }

   }
    }
