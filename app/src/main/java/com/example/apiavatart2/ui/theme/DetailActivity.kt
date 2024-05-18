package com.example.apiavatart2.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
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
        val gender = intent.getStringExtra("gender")
        val hair = intent.getStringExtra("hair")
        val weapon = intent.getStringExtra("weapon")
        val profession = intent.getStringExtra("profession")
        val position = intent.getStringExtra("position")
        val first = intent.getStringExtra("first")
        val allies = intent.getStringArrayListExtra("allies")
        val enemies = intent.getStringArrayListExtra("enemies")

        binding.textViewName.text = name
        binding.textViewAffiliation.text = affiliation
        binding.textViewGender.text = "Gender: $gender"
        binding.textViewHair.text = "Hair: $hair"
        binding.textViewWeapon.text = "Weapon: $weapon"
        binding.textViewProfession.text = "Profession: $profession"
        binding.textViewPosition.text = "Position: $position"
        binding.textViewFirst.text = "First Appearance: $first"
        binding.textViewAllies.text = "Allies: ${allies?.joinToString(", ")}"
        binding.textViewEnemies.text = "Enemies: ${enemies?.joinToString(", ")}"

        Picasso.get().load(photoUrl).into(binding.imageViewAvatar)
    }
}
