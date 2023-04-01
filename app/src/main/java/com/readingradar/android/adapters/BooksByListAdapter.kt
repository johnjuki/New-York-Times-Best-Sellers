package com.readingradar.android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.readingradar.android.data.models.Books
import com.readingradar.android.databinding.BooksByListViewHolderBinding
import com.readingradar.android.utils.BooksClickListener

class BooksByListAdapter(
    private val books: List<Books>,
    private val parentFragment: Fragment,
    private val clickListener: BooksClickListener
) :
    RecyclerView.Adapter<BooksByListAdapter.ViewHolder>() {

    class ViewHolder(binding: BooksByListViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rank = binding.rankTextView
        val bookCard = binding.booksCardView
        val imageCover = binding.bookImageView
        val title = binding.titleTextView
        val contributor = binding.contributorTextView
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
        val weeksOnList = "Weeks on list: " + if (book.weeksOnList == 0) "n/a" else book.weeksOnList.toString()

        Glide.with(parentFragment)
            .load(book.bookImage)
            .override(book.imageWidth, book.imageHeight)
            .into(holder.imageCover)

        holder.rank.text = book.rank.toString()
        holder.title.text = book.title
        holder.contributor.text = book.contributor
        holder.description.text = book.description
        holder.lastWeekRank.text = rankLastWeek
        holder.weeksOnListRank.text = weeksOnList
    }

    override fun getItemCount() = books.size

}