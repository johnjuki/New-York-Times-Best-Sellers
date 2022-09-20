package com.example.nytbestsellers.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytbestsellers.adapter.BestSellersListsAdapter
import com.example.nytbestsellers.databinding.FragmentHomeBinding
import com.example.nytbestsellers.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var bestSellersListsAdapter: BestSellersListsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.listsRecyclerView.layoutManager = layoutManager
        bestSellersListsAdapter = BestSellersListsAdapter(this)
        binding.listsRecyclerView.adapter = bestSellersListsAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.status.observe(viewLifecycleOwner) {
            bestSellersListsAdapter.addData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}