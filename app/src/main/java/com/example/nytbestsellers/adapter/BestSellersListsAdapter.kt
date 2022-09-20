package com.example.nytbestsellers.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nytbestsellers.databinding.ListsViewHolderBinding
import com.example.nytbestsellers.network.Lists

class BestSellersListsAdapter(private val parentFragment: Fragment) :
    RecyclerView.Adapter<BestSellersListsAdapter.DataViewHolder>() {

    private var bestSellersList: List<Lists>? = ArrayList()

    class DataViewHolder(binding: ListsViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        val listName = binding.listNameTextView
        val booksRecyclerView = binding.booksRecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ListsViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val sellers = bestSellersList ?: return

        val bestSellersLists = sellers[position]

        holder.listName.text = bestSellersLists.displayName

        val bestSellersBooksAdapter = BestSellersBooksAdapter(bestSellersLists.books, parentFragment)
        holder.booksRecyclerView.layoutManager =
            LinearLayoutManager(parentFragment.context, LinearLayoutManager.HORIZONTAL, false)
        holder.booksRecyclerView.adapter = bestSellersBooksAdapter
    }

    override fun getItemCount(): Int = bestSellersList?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<Lists>) {
        bestSellersList = list
        notifyDataSetChanged()
    }
}
