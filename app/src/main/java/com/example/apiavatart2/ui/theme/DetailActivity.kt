package com.example.apiavatart2.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.apiavatart2.R
import com.example.apiavatart2.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val photoUrl = intent.getStringExtra("photoUrl")
        val affiliation = intent.getStringExtra("affiliation")

        binding.textViewName.text = name
        binding.textViewAffiliation.text = affiliation
        Picasso.get().load(photoUrl).into(binding.imageViewAvatar)
    }
}
