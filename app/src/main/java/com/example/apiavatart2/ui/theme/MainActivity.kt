package com.example.apiavatart2.ui.theme

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiavatart2.data.remote.AvatarApi
import com.example.apiavatart2.data.remote.model.AvatarDto
import com.example.apiavatart2.databinding.ActivityMainBinding
import com.example.apiavatart2.data.remote.AvatarAdapter
import com.example.apiavatart2.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val avatarApi = retrofit.create(AvatarApi::class.java)

        val call: Call<List<AvatarDto>> = avatarApi.getCharacters()
        call.enqueue(object: Callback<List<AvatarDto>> {
            override fun onResponse(p0: Call<List<AvatarDto>>, p1: Response<List<AvatarDto>>) {
                if (p1.isSuccessful) {
                    val avatars = p1.body() ?: emptyList()
                    setupRecyclerView(avatars)
                    Toast.makeText(
                        this@MainActivity,
                        "Conectado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error en la respuesta!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<List<AvatarDto>>, p1: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "No hay conexión disponible!", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun setupRecyclerView(avatars: List<AvatarDto>) {
        val recyclerView = binding.recyclerViewAvatars
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AvatarAdapter(avatars)
    }
}
