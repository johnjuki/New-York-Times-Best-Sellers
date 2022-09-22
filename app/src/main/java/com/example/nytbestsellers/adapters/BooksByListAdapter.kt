package com.example.nytbestsellers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytbestsellers.data.models.Books
import com.example.nytbestsellers.databinding.BooksByListViewHolderBinding
import com.example.nytbestsellers.utils.BooksClickListener

class BooksByListAdapter(
    private val books: List<Books>,
    private val parentFragment: Fragment,
    private val clickListener: BooksClickListener
) :
    RecyclerView.Adapter<BooksByListAdapter.ViewHolder>() {

    class ViewHolder(binding: BooksByListViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bookCard = binding.booksCardView
        val imageCover = binding.bookImageView
        val title = binding.titleTextView
        val contributor = binding.contributorTextView
        val publisher = binding.publisherTextView
        val description = binding.descriptionTextView
        val lastWeekRank = binding.lastWeekRankTextView
        val weeksOnListRank = binding.weeksOnList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BooksByListViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]

        holder.bookCard.setOnClickListener {
            clickListener.onClick(book)
        }

        val rankLastWeek =
            "Last Week: " + if (book.rankLastWeek == 0) "n/a" else book.rankLastWeek.toString()
        val weeksOnList = "Weeks on list: " + book.weeksOnList.toString()

        Glide.with(parentFragment)
            .load(book.bookImage)
            .into(holder.imageCover)

        holder.title.text = book.title
        holder.contributor.text = book.contributor
        holder.publisher.text = book.publisher
        holder.description.text = book.description
        holder.lastWeekRank.text = rankLastWeek
        holder.weeksOnListRank.text = weeksOnList
    }

    override fun getItemCount() = books.size

}