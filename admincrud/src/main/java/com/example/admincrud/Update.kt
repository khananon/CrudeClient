package com.example.admincrud

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admincrud.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
binding.Updatebutton.setOnClickListener {
    val  uid= binding.uid.text.toString()
    val  name= binding.name.text.toString()
    val Class= binding.editTextClass.text.toString()

      updateData(name,uid,Class)

}


    }
    private fun updateData(Name: String,uid:String,Class:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("Student info")
        val studentData= mapOf<String,String>( "studentName" to Name,"uid" to uid, "class" to Class )
        databaseReference.child(uid).updateChildren(studentData).addOnSuccessListener {
            binding.name.text.clear()
            binding.uid.text.clear()
            binding.editTextClass.text.clear()
            Toast.makeText(this, "Data updated Successfully!!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "unable to update", Toast.LENGTH_SHORT).show()
        }
    }
}