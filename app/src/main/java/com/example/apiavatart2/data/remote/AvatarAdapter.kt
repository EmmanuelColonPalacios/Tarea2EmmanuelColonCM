package com.example.apiavatart2.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiavatart2.R
import com.example.apiavatart2.data.remote.model.AvatarDto
import com.example.apiavatart2.ui.theme.DetailActivity
import com.squareup.picasso.Picasso

class AvatarAdapter(private val avatars: List<AvatarDto>) : RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return AvatarViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        val avatar = avatars[position]
        holder.bind(avatar)
    }

    override fun getItemCount(): Int = avatars.size

    class AvatarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewAffiliation: TextView = itemView.findViewById(R.id.textViewAffiliation)

        fun bind(avatar: AvatarDto) {
            textViewName.text = avatar.name
            textViewAffiliation.text = avatar.affiliation
            Picasso.get().load(avatar.photoUrl).into(imageViewAvatar)

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("name", avatar.name)
                    putExtra("photoUrl", avatar.photoUrl)
                    putExtra("affiliation", avatar.affiliation)
                }
                context.startActivity(intent)
            }
        }
    }
}
