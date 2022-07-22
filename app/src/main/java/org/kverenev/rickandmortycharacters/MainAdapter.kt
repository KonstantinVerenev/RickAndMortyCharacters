package org.kverenev.rickandmortycharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import org.kverenev.rickandmortycharacters.databinding.RvItemBinding
import org.kverenev.rickandmortycharacters.network.Character

class MainAdapter(private val characterList: List<Character>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    class MainViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.name.text = character.name
            if (character.image.isNotBlank()) {
                binding.image.load(character.image) {
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}