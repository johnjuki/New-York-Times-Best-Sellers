package com.example.nytbestsellers.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.example.nytbestsellers.R
import com.example.nytbestsellers.data.models.Books
import com.example.nytbestsellers.databinding.FragmentBookDetailsBinding
import com.example.nytbestsellers.viewmodel.MainViewModel

class BookDetailsFragment : Fragment() {
    private var _binding: FragmentBookDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: BookDetailsFragmentArgs by navArgs()

    private val viewModel: MainViewModel by viewModels()

    private lateinit var bookDetails: Books

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookDetails = args.bookArgument

        val url = "https://www.googleapis.com/books/v1/volumes?q=isbn:${bookDetails.isbn}"

        viewModel.getTheBookDescription(url)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topToolBar.title = bookDetails.title
        binding.topToolBar.setNavigationOnClickListener { this.findNavController().navigateUp() }

        Glide.with(this)
            .load(bookDetails.bookImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.bookImageView)

        binding.titleTextView.text = bookDetails.title
        binding.contributorTextView.text = bookDetails.contributor
        binding.publisherTextView.text = bookDetails.publisher

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPref.edit()

        val savedDescription = sharedPref.getString(bookDetails.isbn, null)

        if (savedDescription == null) {
            viewModel.bookDescription.observe(viewLifecycleOwner) { description ->
                editor.putString(bookDetails.isbn, description)
                editor.apply()

                binding.synopsisTextview.text = description
            }
        }

        binding.synopsisTextview.text = savedDescription
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
