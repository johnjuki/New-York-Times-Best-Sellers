package com.example.nytbestsellers.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytbestsellers.adapter.BestSellersAdapter
import com.example.nytbestsellers.databinding.FragmentHomeBinding
import com.example.nytbestsellers.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var bestSellersAdapter: BestSellersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.bestSellersRecyclerView.layoutManager = layoutManager
        bestSellersAdapter = BestSellersAdapter(null, this)
        binding.bestSellersRecyclerView.adapter = bestSellersAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.status.observe(viewLifecycleOwner) {
            bestSellersAdapter.setBestSellersData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}