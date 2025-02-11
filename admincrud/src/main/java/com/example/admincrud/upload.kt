package com.example.admincrud

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admincrud.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class upload : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload)
        binding= ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Savebutton.setOnClickListener {
            val studentName= binding.name.text.toString()
            val uid= binding.uid.text.toString()
            val className= binding.editTextClass.text.toString()

            databaseReference=FirebaseDatabase.getInstance().getReference("Student info")
            val studentData= StudentData(studentName,uid,className)
            databaseReference.child(uid).setValue(studentData).addOnSuccessListener{
                binding.name.text.clear()
                binding.uid.text.clear()
                binding.editTextClass.text.clear()
                Toast.makeText(this, "successfully uploaded", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }




        }



    }
}