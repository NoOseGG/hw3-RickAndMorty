package com.bignerdranch.android.rickandmorty.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bignerdranch.android.rickandmorty.databinding.ItemCharacterBinding
import com.bignerdranch.android.rickandmorty.model.PersonItem

class PersonAdapter(
    private val onClick: (PersonItem.Person) -> Unit,
) : ListAdapter<PersonItem.Person, PersonViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<PersonItem.Person>() {
            override fun areItemsTheSame(oldItem: PersonItem.Person, newItem: PersonItem.Person): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PersonItem.Person, newItem: PersonItem.Person): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

class PersonViewHolder(
    private val binding: ItemCharacterBinding,
    private val onClick: (PersonItem.Person) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonItem.Person) {
        binding.imgAvatar.load(item.image)
        binding.tvName.text = item.name
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}