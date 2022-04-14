package com.bignerdranch.android.rickandmorty.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.rickandmorty.R
import com.bignerdranch.android.rickandmorty.adapter.PersonAdapter
import com.bignerdranch.android.rickandmorty.addPaginationScrollListener
import com.bignerdranch.android.rickandmorty.databinding.FragmentListBinding
import com.bignerdranch.android.rickandmorty.model.ListPerson
import com.bignerdranch.android.rickandmorty.model.PersonItem
import com.bignerdranch.android.rickandmorty.retrofit.RickAndMortyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val adapter by lazy { PersonAdapter {
        findNavController().navigate(
            R.id.action_listFragment_to_infoFragment,
            bundleOf(InfoFragment.KEY_PERSON to it)
        )
    } }

    private var isLoading = false
    private var count = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayout = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = linearLayout
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addPaginationScrollListener(linearLayout, 10) {
            if (!isLoading) {
                isLoading = true

                count++
                loadRickAndMortyCharacters(count)
                Log.d("MyTag", "$count")

                isLoading = false
            }
        }

        loadRickAndMortyCharacters(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadRickAndMortyCharacters(page: Int) {
        RickAndMortyService.getInstance()
            .getAllCharacters(page).enqueue(object : Callback<ListPerson> {
                override fun onResponse(call: Call<ListPerson>, response: Response<ListPerson>) {
                    if(response.isSuccessful) {
                        val listPerson = response.body()
                        val currentList = adapter.currentList.toList()
                        val listResult = currentList.plus(listPerson?.results as List<PersonItem.Person>)


                        adapter.submitList(listResult)
                    }

                }

                override fun onFailure(call: Call<ListPerson>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}
