package com.example.nytbestsellers.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nytbestsellers.databinding.ParentViewholderBinding
import com.example.nytbestsellers.network.Lists

class ParentAdapter(private val parentFragment: Fragment) :
    RecyclerView.Adapter<ParentAdapter.DataViewHolder>() {

    private var bestSellersList: List<Lists>? = ArrayList()

    class DataViewHolder(binding: ParentViewholderBinding) : RecyclerView.ViewHolder(binding.root) {
        val listName = binding.listNameTextView
        val childRecyclerView = binding.childRecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ParentViewholderBinding.inflate(
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

        val childAdapter = ChildAdapter(bestSellersLists.books, parentFragment)
        holder.childRecyclerView.layoutManager =
            LinearLayoutManager(parentFragment.context, LinearLayoutManager.HORIZONTAL, false)
        holder.childRecyclerView.adapter = childAdapter
    }

    override fun getItemCount(): Int = bestSellersList?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<Lists>) {
        bestSellersList = list
        notifyDataSetChanged()
    }
}
