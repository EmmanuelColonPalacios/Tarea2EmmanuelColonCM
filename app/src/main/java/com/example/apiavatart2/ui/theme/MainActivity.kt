package com.example.apiavatart2.ui.theme

import android.os.Bundle
import android.util.Log
import android.view.View
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
    private lateinit var avatarApi: AvatarApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        avatarApi = retrofit.create(AvatarApi::class.java)

        binding.buttonRetryMain.setOnClickListener {
            fetchCharacters()
        }

        fetchCharacters()
    }

    private fun fetchCharacters() {
        binding.buttonRetryMain.visibility = View.GONE
        val call: Call<List<AvatarDto>> = avatarApi.getCharacters()
        call.enqueue(object: Callback<List<AvatarDto>> {
            override fun onResponse(call: Call<List<AvatarDto>>, response: Response<List<AvatarDto>>) {
                if (response.isSuccessful) {
                    val avatars = response.body() ?: emptyList()
                    setupRecyclerView(avatars)
                    Toast.makeText(this@MainActivity, "Conectado!", Toast.LENGTH_SHORT).show()
                    Log.d("API Response", "Response: $avatars")
                } else {
                    Toast.makeText(this@MainActivity, "Error en la respuesta!", Toast.LENGTH_SHORT).show()
                    binding.buttonRetryMain.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<AvatarDto>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No hay conexión disponible!", Toast.LENGTH_SHORT).show()
                binding.buttonRetryMain.visibility = View.VISIBLE
            }
        })
    }

    private fun setupRecyclerView(avatars: List<AvatarDto>) {
        val recyclerView = binding.recyclerViewAvatars
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AvatarAdapter(avatars)
    }
}
