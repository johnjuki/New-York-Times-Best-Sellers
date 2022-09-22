package com.example.nytbestsellers.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytbestsellers.MainActivity
import com.example.nytbestsellers.adapters.BooksByListAdapter
import com.example.nytbestsellers.databinding.FragmentBooksByListBinding
import com.example.nytbestsellers.utils.BooksClickListener

class BooksByListFragment : Fragment() {
    private var _binding: FragmentBooksByListBinding? = null
    private val binding get() = _binding!!

    private lateinit var booksByListAdapter: BooksByListAdapter

    private val args: BooksByListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBooksByListBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.booksByListRecyclerView.layoutManager = layoutManager
        booksByListAdapter = BooksByListAdapter(args.booksArgument.toList(), this, BooksClickListener { book ->
            this.findNavController().navigate(
                BooksByListFragmentDirections.actionBooksByListFragmentToBookDetailsFragment2(book)
            )
        })
        binding.booksByListRecyclerView.adapter = booksByListAdapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = args.listNameArg
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}