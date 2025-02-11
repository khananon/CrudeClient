package com.example.admincrud

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admincrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

  binding.upbutton.setOnClickListener {
      var intent = Intent(this, upload::class.java)
      startActivity(intent)

  }
        binding.Updatebutton.setOnClickListener {
      var intent = Intent(this, Update::class.java)
      startActivity(intent)

    }
        binding.Deletebutton.setOnClickListener {
            var intent = Intent(this, Delete::class.java)
            startActivity(intent)

        }
}
}