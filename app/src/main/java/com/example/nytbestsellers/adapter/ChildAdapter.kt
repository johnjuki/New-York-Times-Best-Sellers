package com.example.nytbestsellers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytbestsellers.databinding.ChildViewHolderBinding
import com.example.nytbestsellers.network.Books

class ChildAdapter(private val books: List<Books>, private val parentFragment: Fragment) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    class ViewHolder(binding: ChildViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageCover = binding.bookImageView
        val title = binding.titleTextView
        val author = binding.authorTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChildViewHolderBinding.inflate(
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
        holder.author.text = book.contributor

    }

    override fun getItemCount(): Int {
        return books.size
    }

}
