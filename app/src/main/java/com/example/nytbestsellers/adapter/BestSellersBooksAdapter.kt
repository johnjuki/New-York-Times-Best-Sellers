package com.example.nytbestsellers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytbestsellers.databinding.BooksViewHolderBinding
import com.example.nytbestsellers.network.Books

class BestSellersBooksAdapter(private val books: List<Books>, private val parentFragment: Fragment) :
    RecyclerView.Adapter<BestSellersBooksAdapter.ViewHolder>() {

    class ViewHolder(binding: BooksViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageCover = binding.bookImageView
        val title = binding.titleTextView
        val contributor = binding.contributorTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BooksViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]

        Glide.with(parentFragment)
            .load(book.bookImage)
            .override(book.imageWidth, book.imageHeight)
            .into(holder.imageCover)

        holder.title.text = book.title
        holder.contributor.text = book.contributor

    }

    override fun getItemCount(): Int {
        return books.size
    }

}
