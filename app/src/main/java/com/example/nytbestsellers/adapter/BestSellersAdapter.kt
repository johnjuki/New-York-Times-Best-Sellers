package com.example.nytbestsellers.adapter
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.nytbestsellers.databinding.BestSellersViewHolderBinding
//import com.example.nytbestsellers.network.Lists
//
//class BestSellersAdapter(private var bestSellersList: List<Lists>?, private  val parentFragment: Fragment) :
//    RecyclerView.Adapter<BestSellersAdapter.ViewHolder>() {
//
//    class ViewHolder(binding: BestSellersViewHolderBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        val imageCover = binding.bookImageView
//        val title = binding.titleTextView
//        val publisher = binding.descriptionTextView
//        val description = binding.descriptionTextView
//        val lastRankWeek= binding.lastWeekRankTextView
//        val weeksOnList = binding.weeksOnList
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(BestSellersViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val bestSellersDetails = bestSellersList ?: return
//
//        val booksList = bestSellersDetails[position]
//
//
//        for (j in listOf(booksList)) {
//            Glide.with(parentFragment)
//                .load(j.books[position].bookImage)
//                .into(holder.imageCover)
//
//            holder.title.text = j.books[position].title
//            holder.author.text = j.books[position].author
//            holder.publisher.text = j.books[position].publisher
//            holder.description.text = j.books[position].description
//            holder.lastRankWeek.text = j.books[position].rankLastWeek.toString()
//            holder.weeksOnList.text = j.books[position].weeksOnList.toString()
//
//        }
//
//
//    }
//
//    override fun getItemCount(): Int {
//        val notNullBestSellers = mutableListOf<List<Lists>>()
//
//        var size = 0
//
//        if (bestSellersList?.size != 0) {
//            bestSellersList?.let { notNullBestSellers.add(it) }
//        }
//
//        for ( i in notNullBestSellers) {
//            for (j in i) {
//                size = j.books.size
//            }
//        }
//
//        return size
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setBestSellersData(bestSellersData: List<Lists>) {
//        bestSellersList = bestSellersData
//        notifyDataSetChanged()
//    }
//}
