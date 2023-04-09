package com.readingradar.android.adapters


//class BestSellersListsAdapter(private val parentFragment: Fragment) :
//    RecyclerView.Adapter<BestSellersListsAdapter.DataViewHolder>() {
//
//    private var bestSellersList: List<BooksList>? = ArrayList()
//
//    class DataViewHolder(binding: ListsViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
//        val listName = binding.listNameTextView
//        val seeAll = binding.seeAllTextView
//        val booksRecyclerView = binding.booksRecyclerView
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        return DataViewHolder(
//            ListsViewHolderBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        val sellers = bestSellersList ?: return
//
//        val bestSellersLists = sellers[position]
//
//        val listName = bestSellersLists.displayName
//
//        holder.listName.text = listName
//
//        val actions = HomeFragmentDirections.actionHomeFragment2ToBooksByListFragment(
//            bestSellersLists.books.toTypedArray(),
//            listName
//        )
//
//        holder.listName.setOnClickListener { parentFragment.findNavController().navigate(actions) }
//
//        holder.seeAll.setOnClickListener { parentFragment.findNavController().navigate(actions) }
//
//        val bestSellersBooksAdapter =
//            BestSellersBooksAdapter(
//                bestSellersLists.books,
//                parentFragment,
//                BooksClickListener { book ->
//                    parentFragment.findNavController().navigate(
//                        HomeFragmentDirections.actionHomeFragment2ToBookDetailsFragment2(
//                            book
//                        )
//                    )
//                }
//            )
//        bestSellersBooksAdapter.stateRestorationPolicy = StateRestorationPolicy.ALLOW
//        holder.booksRecyclerView.layoutManager =
//            LinearLayoutManager(parentFragment.context, LinearLayoutManager.HORIZONTAL, false)
//        holder.booksRecyclerView.adapter = bestSellersBooksAdapter
//    }
//
//    override fun getItemCount(): Int = bestSellersList?.size ?: 0
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun addData(list: List<BooksList>) {
//        bestSellersList = list
//        notifyDataSetChanged()
//    }
//}
