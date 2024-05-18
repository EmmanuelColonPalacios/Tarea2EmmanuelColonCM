package com.example.apiavatart2.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.apiavatart2.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import com.example.apiavatart2.data.remote.model.AvatarDto
import com.example.apiavatart2.data.remote.AvatarApi
import com.example.apiavatart2.data.remote.AvatarAdapter

class DetailActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
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

        binding.textViewName.text = name ?: "Nombre no disponible"
        binding.textViewAffiliation.text = affiliation ?: "Afiliación no disponible"
        binding.textViewGender.text = gender?.let { "Género: $it" } ?: "Género no disponible"
        binding.textViewHair.text = hair?.let { "Cabello: $it" } ?: "Cabello no disponible"
        binding.textViewWeapon.text = weapon?.let { "Arma: $it" } ?: "Arma no disponible"
        binding.textViewProfession.text = profession?.let { "Profesión: $it" } ?: "Profesión no disponible"
        binding.textViewPosition.text = position?.let { "Posición: $it" } ?: "Posición no disponible"
        binding.textViewFirst.text = first?.let { "Primera aparición: $it" } ?: "Primera aparición no disponible"
        binding.textViewAllies.text = allies?.let { "Aliados: ${it.joinToString(", ")}" } ?: "Aliados no disponibles"
        binding.textViewEnemies.text = enemies?.let { "Enemigos: ${it.joinToString(", ")}" } ?: "Enemigos no disponibles"


        Picasso.get().load(photoUrl).into(binding.imageViewAvatar)
    }
}
