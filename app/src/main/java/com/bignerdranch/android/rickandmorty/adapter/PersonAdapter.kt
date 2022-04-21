package com.bignerdranch.android.rickandmorty.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bignerdranch.android.rickandmorty.databinding.ItemCharacterBinding
import com.bignerdranch.android.rickandmorty.databinding.ItemLoadingBinding
import com.bignerdranch.android.rickandmorty.model.PersonItem

class PersonAdapter(
    private val onClick: (PersonItem.Person) -> Unit,
) : ListAdapter<PersonItem, RecyclerView.ViewHolder>(DIFF_UTIL) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            PersonItem.Loading -> TYPE_LOADING
            is PersonItem.Person -> TYPE_PERSON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PERSON -> {
                PersonViewHolder(
                    binding = ItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick = onClick
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder(
                    binding = ItemLoadingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> error("Incorrect viewType = $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PersonViewHolder) {
            holder.bind(getItem(position) as PersonItem.Person)
        }
    }

    companion object {

        private const val TYPE_PERSON = 0
        private const val TYPE_LOADING = 1

        val DIFF_UTIL = object : DiffUtil.ItemCallback<PersonItem>() {
            override fun areItemsTheSame(oldItem: PersonItem, newItem: PersonItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PersonItem, newItem: PersonItem): Boolean {
                return oldItem == newItem
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

class LoadingViewHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)