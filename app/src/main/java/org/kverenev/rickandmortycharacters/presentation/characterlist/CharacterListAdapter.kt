package org.kverenev.rickandmortycharacters.presentation.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import org.kverenev.rickandmortycharacters.domain.models.CharacterItem
import org.kverenev.rickandmortycharacters.databinding.RvCharacterItemBinding

class CharacterListAdapter(
    private val characterList: List<CharacterItem>,
    private val onClickDetails: (id: String) -> Unit
) :
    RecyclerView.Adapter<CharacterListAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvCharacterItemBinding.inflate(inflater, parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class MainViewHolder(private val binding: RvCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterItem) {
            binding.name.text = character.name

            if (character.image.isNotBlank()) {
                binding.characterImage.load(character.image) {
                    transformations(CircleCropTransformation())
                }
            }

            binding.root.setOnClickListener {
                onClickDetails(character.id)
            }
        }
    }
}