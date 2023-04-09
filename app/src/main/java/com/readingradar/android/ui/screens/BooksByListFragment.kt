package com.readingradar.android.ui.screens

//class BooksByListFragment : Fragment() {
//    private var _binding: FragmentBooksByListBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var booksByListAdapter: BooksByListAdapter
//
//    private val args: BooksByListFragmentArgs by navArgs()
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentBooksByListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.materialToolBar.title = args.listNameArg
//        binding.materialToolBar.setNavigationOnClickListener {
//            this.findNavController().navigateUp()
//        }
//
//        val layoutManager = LinearLayoutManager(requireContext())
//        binding.booksByListRecyclerView.layoutManager = layoutManager
//        booksByListAdapter =
//            BooksByListAdapter(args.booksArgument.toList(), this, BooksClickListener { book ->
//                this.findNavController().navigate(
//                    BooksByListFragmentDirections.actionBooksByListFragmentToBookDetailsFragment2(
//                        book
//                    )
//                )
//            })
//        booksByListAdapter.stateRestorationPolicy =
//            RecyclerView.Adapter.StateRestorationPolicy.ALLOW
//        binding.booksByListRecyclerView.adapter = booksByListAdapter
//
//    }
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//}