package com.example.nytbestsellers.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytbestsellers.databinding.BestSellersViewHolderBinding
import com.example.nytbestsellers.network.Books

class BestSellersAdapter(private var bestSellersList: List<Books>?, private  val parentFragment: Fragment) :
    RecyclerView.Adapter<BestSellersAdapter.ViewHolder>() {

    class ViewHolder(binding: BestSellersViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageCover: ImageView = binding.bookImageView
        val title = binding.titleTextView
        val author = binding.authorTextView
        val publisher = binding.descriptionTextView
        val description = binding.descriptionTextView
        val lastRankWeek= binding.lastWeekRankTextView
        val weeksOnList = binding.weeksOnList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BestSellersViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bestSellersDetails = bestSellersList ?: return

        val book = bestSellersDetails[position]

        Glide.with(parentFragment)
            .load(book.bookImage)
            .into(holder.imageCover)

        holder.title.text = book.title
        holder.author.text = book.author
        holder.publisher.text = book.publisher
        holder.description.text = book.description
        holder.lastRankWeek.text = book.rankLastWeek.toString()
        holder.weeksOnList.text = book.weeksOnList.toString()
    }

    override fun getItemCount(): Int {
        return bestSellersList?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBestSellersData(bestSellersData: List<Books>) {
        bestSellersList = bestSellersData
        notifyDataSetChanged()
    }
}
