package com.example.crudeclient

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudeclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val uid :String= binding.SearcheditText.text.toString()
            if(uid.isNotEmpty()){
            readData(uid)
        }
        else{
                Toast.makeText(this, "Please enter the uid", Toast.LENGTH_SHORT).show()
            }
        }



        }
    private fun readData(StudentUid : String){
        databaseReference= FirebaseDatabase.getInstance().getReference("Student info")
        databaseReference.child(StudentUid).get().addOnSuccessListener {
            if(it.exists()){
                val StudentName=it.child("studentName").value
                val uid=it.child("uid").value
                val Class =it.child("class").value
                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()
                binding.SearcheditText.text.clear()
                binding.NameShow.text= StudentName.toString()
                binding.UidShow.text=uid.toString()
                binding.ClassShow.text=Class.toString()
            }else{
                Toast.makeText(this, "Result not  Found", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
    }
