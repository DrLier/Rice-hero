package com.example.capstone.mainpage.ui.pestdisease

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone.R
import com.example.capstone.databinding.ItemPlantDiseaseBinding
import com.example.capstone.mainpage.ui.detailpestdisease.DetailPestDisease
import com.example.capstone.model.PlantDisease

class AdapterPlantDisease(private val listPlantDisease : ArrayList<PlantDisease>) : RecyclerView.Adapter<AdapterPlantDisease.ViewHolder>() {

    class ViewHolder(var binding : ItemPlantDiseaseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlantDiseaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listPlantDisease.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, type, photo) = listPlantDisease[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.binding.imagePlantDisease)
        holder.binding.typePlantDisease.text = type
        holder.binding.plantDisease.text = name
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailPestDisease::class.java)
            intent.putExtra(DATA, listPlantDisease[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object {
        val DATA = "DATA"
    }
}