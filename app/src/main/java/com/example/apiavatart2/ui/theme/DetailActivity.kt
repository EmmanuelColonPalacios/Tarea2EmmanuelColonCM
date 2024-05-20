package com.example.apiavatart2.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.apiavatart2.data.remote.AvatarApi
import com.example.apiavatart2.data.remote.model.AvatarDto
import com.example.apiavatart2.databinding.ActivityDetailBinding
import com.example.apiavatart2.util.Constants
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var avatarApi: AvatarApi
    private lateinit var characterId: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterId = intent.getStringExtra("id") ?: ""

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        avatarApi = retrofit.create(AvatarApi::class.java)

        binding.buttonRetryDetail.setOnClickListener {
            fetchCharacterDetails(characterId)
        }

        if (characterId.isNotEmpty()) {
            fetchCharacterDetails(characterId)
        } else {
            Toast.makeText(this, "ID no disponible", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchCharacterDetails(id: String) {
        binding.buttonRetryDetail.visibility = View.GONE
        val call: Call<AvatarDto> = avatarApi.getCharacterDetail(id)
        call.enqueue(object: Callback<AvatarDto> {
            override fun onResponse(call: Call<AvatarDto>, response: Response<AvatarDto>) {
                if (response.isSuccessful) {
                    val character = response.body()
                    if (character != null) {
                        updateUI(character)
                    } else {
                        Toast.makeText(this@DetailActivity, "Datos no disponibles", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@DetailActivity, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                    binding.buttonRetryDetail.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<AvatarDto>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                Log.e("DetailActivity", "Error fetching character details", t)
                binding.buttonRetryDetail.visibility = View.VISIBLE
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(character: AvatarDto) {
        binding.textViewName.text = character.name ?: "Nombre no disponible"
        binding.textViewAffiliation.text = character.affiliation ?: "Afiliación no disponible"
        binding.textViewGender.text = character.gender?.let { "Género: $it" } ?: "Género no disponible"
        binding.textViewHair.text = character.hair?.let { "Cabello: $it" } ?: "Cabello no disponible"
        binding.textViewWeapon.text = character.weapon?.let { "Arma: $it" } ?: "Arma no disponible"
        binding.textViewProfession.text = character.profession?.let { "Profesión: $it" } ?: "Profesión no disponible"
        binding.textViewPosition.text = character.position?.let { "Posición: $it" } ?: "Posición no disponible"
        binding.textViewFirst.text = character.first?.let { "Primera aparición: $it" } ?: "Primera aparición no disponible"
        binding.textViewAllies.text = character.allies?.let { "Aliados: ${it.joinToString(", ")}" } ?: "Aliados no disponibles"
        binding.textViewEnemies.text = character.enemies?.let { "Enemigos: ${it.joinToString(", ")}" } ?: "Enemigos no disponibles"

        Picasso.get().load(character.photoUrl).into(binding.imageViewAvatar)
    }
}
